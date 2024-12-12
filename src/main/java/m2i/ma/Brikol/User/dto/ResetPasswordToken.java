package m2i.ma.Brikol.User.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResetPasswordToken {
    private String token;
    private String uid;
}
