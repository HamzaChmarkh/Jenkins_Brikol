package m2i.ma.Brikol.User.service;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.Client.Client;
import m2i.ma.Brikol.Client.ClientRepository;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Freelancer.FreelancerRepository;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.UtilisateurRepository;
import m2i.ma.Brikol.User.confirmation.ConfirmationToken;
import m2i.ma.Brikol.User.confirmation.ConfirmationTokenRepository;
import m2i.ma.Brikol.User.confirmation.ConfirmationTokenService;
import m2i.ma.Brikol.User.confirmation.EmailService;
import m2i.ma.Brikol.User.dto.*;
import m2i.ma.Brikol.User.mapper.UtilisateurMapper;
import m2i.ma.Brikol.config.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtService jwtService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    @Transactional
    @Override
    public ResponseEntity<JwtAuthenticationResponse> signIn(SignInRequest signInRequest) {

        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(signInRequest.getEmail());
        if(!utilisateur.isPresent()){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        if(!utilisateur.get().isEmailVerifier()){
            return new ResponseEntity<>( HttpStatus.FORBIDDEN);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );

        var user = utilisateur.get();

        var jwt = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        System.out.println("Generated JWT: " + jwt);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccess_token(jwt);
        jwtAuthenticationResponse.setRefresh_token(refreshToken);
        return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getRefresh_token());
        Utilisateur user = utilisateurRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getRefresh_token(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setAccess_token(jwt);
            jwtAuthenticationResponse.setRefresh_token(refreshTokenRequest.getRefresh_token());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<String> signUp(SignUpRequest signUpRequest) throws MessagingException {

        Optional<Utilisateur> utilisateur;

        utilisateur = utilisateurRepository.findByEmail(signUpRequest.getEmail());

        if (utilisateur.isPresent()) {
            return new ResponseEntity<>("Email already in use", HttpStatus.CONFLICT);
        }

        Utilisateur savedUtilisateur;
        if(signUpRequest.getAccountType().equals(Role.Freelancer.toString())){
            Freelancer freelancer = new Freelancer();
            freelancer.setNom(signUpRequest.getName());
            freelancer.setEmail(signUpRequest.getEmail());
            freelancer.setRole(Role.valueOf(signUpRequest.getAccountType()));
            freelancer.setMotDePasse(new BCryptPasswordEncoder(12).encode(signUpRequest.getPassword()));
            savedUtilisateur = utilisateurRepository.save(freelancer);
        }else{
            Client client = new Client();
            client.setNom(signUpRequest.getName());
            client.setEmail(signUpRequest.getEmail());
            client.setRole(Role.valueOf(signUpRequest.getAccountType()));
            client.setMotDePasse(new BCryptPasswordEncoder(12).encode(signUpRequest.getPassword()));
            savedUtilisateur = utilisateurRepository.save(client);
        }

        //Generate a confirmation token

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusDays(1))
                .utilisateur(savedUtilisateur)
                .build();
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        emailService.sendEmailConfirmation(signUpRequest.getEmail(), token, savedUtilisateur.getNom());

        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
    }

    @Transactional
    @Override
    public ResponseEntity<String> confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if(confirmationToken.getConfirmedAt() != null){
            return new ResponseEntity<>("Email already confirmed", HttpStatus.CONFLICT);
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        if(expiresAt.isBefore(now)){
            return new ResponseEntity<>("Token expired", HttpStatus.BAD_REQUEST);
        }
        confirmationToken.setConfirmedAt(now);
        utilisateurRepository.enableUtilisateur(confirmationToken.getUtilisateur().getEmail());
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return new ResponseEntity<>("Email confirmed", HttpStatus.OK);
    }
}