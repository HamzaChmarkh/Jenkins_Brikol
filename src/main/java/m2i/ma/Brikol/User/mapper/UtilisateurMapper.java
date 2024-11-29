package m2i.ma.Brikol.User.mapper;

import m2i.ma.Brikol.Client.Client;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.dto.FirstLoginRequest;
import m2i.ma.Brikol.User.dto.JwtAuthenticationResponse;
import m2i.ma.Brikol.User.dto.SignInResponse;
import m2i.ma.Brikol.User.dto.UtilisateurResponse;

import java.util.List;

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
        response.setNewUser(utilisateur.isNewUser());

        return response;
    }

    public static SignInResponse toSignInResponse(Utilisateur utilisateur, JwtAuthenticationResponse jwt) {
        if (utilisateur == null) {
            return null;
        }

        SignInResponse response = new SignInResponse();
        response.setUser(toUtilisateurResponse(utilisateur));
        response.setJwt(jwt);
        return response;
    }

    public static Freelancer toFreelancer(FirstLoginRequest firstLoginRequest, Utilisateur user) {
        Freelancer freelancer =  (Freelancer) user;
        freelancer.setNewUser(false);
        freelancer.setNickName(firstLoginRequest.getNickName());
        freelancer.setPublicEmail(firstLoginRequest.getPublicEmail());
        freelancer.setDescription(firstLoginRequest.getDescription());
        freelancer.setPhoneNumber(firstLoginRequest.getPhoneNumber());
        freelancer.setRegion(firstLoginRequest.getRegion());
        freelancer.setCity(firstLoginRequest.getCity());
        freelancer.setZip(firstLoginRequest.getZip());
        freelancer.setAddress(firstLoginRequest.getAddress());
//        freelancer.setServicesProposes(services);
        return freelancer;
    }

    public static Client toClient(Utilisateur user) {
        Client client =  (Client) user;
        client.setNewUser(false);
        return client;
    }
}
