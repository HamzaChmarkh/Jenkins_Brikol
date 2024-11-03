package m2i.ma.Brikol.User.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
}
