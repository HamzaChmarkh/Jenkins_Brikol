package m2i.ma.Brikol.User.dto;

import lombok.Data;

@Data
public class SignUpResponse {

    private UtilisateurResponse user;
    private JwtAuthenticationResponse jwt;
}
