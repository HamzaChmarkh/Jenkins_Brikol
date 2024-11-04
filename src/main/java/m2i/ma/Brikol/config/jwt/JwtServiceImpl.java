package m2i.ma.Brikol.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    private final UtilisateurRepository utilisateurRepository;

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public String generateToken(UserDetails userDetails){
        Utilisateur utilisateur = utilisateurRepository.findByEmail(userDetails.getUsername()).get();
        Role role = utilisateur.getRole();
        Long id = utilisateur.getId();
        return Jwts.builder().setSubject(userDetails.getUsername())
                .claim("id",id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 100))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails utilisateurDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(utilisateurDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSiginKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSiginKey(){
        byte[] key = Decoders
                .BASE64
                .decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token, UserDetails utilisateurDetails){
        final String utilisateurname = extractUserName(token);
        return (utilisateurname.equals(utilisateurDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }


}
