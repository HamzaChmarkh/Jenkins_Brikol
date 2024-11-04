package m2i.ma.Brikol.User.mapper;

import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.dto.JwtAuthenticationResponse;
import m2i.ma.Brikol.User.dto.SignUpResponse;
import m2i.ma.Brikol.User.dto.UtilisateurResponse;

public class UtilisateurMapper {

    public static UtilisateurResponse toUtilisateurResponse(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        UtilisateurResponse response = new UtilisateurResponse();
        response.setId(utilisateur.getId().toString());
        response.setEmail(utilisateur.getEmail());
        response.setName(utilisateur.getNom());
        response.setImage(utilisateur.getImage());

        return response;
    }

    public static SignUpResponse toSignUpResponse(Utilisateur utilisateur, JwtAuthenticationResponse jwt) {
        if (utilisateur == null) {
            return null;
        }

        SignUpResponse response = new SignUpResponse();
        response.setUser(toUtilisateurResponse(utilisateur));
        response.setJwt(jwt);
        return response;
    }
}
