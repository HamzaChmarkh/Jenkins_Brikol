package m2i.ma.Brikol.User.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String name;
    private String email;
    private String password;
    private String accountType;
}
