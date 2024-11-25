package m2i.ma.Brikol.User.dto;

import lombok.Data;

@Data
public class SignInResponse {

    private UtilisateurResponse user;
    private JwtAuthenticationResponse jwt;
}
