package m2i.ma.Brikol.User.resetpassword;

import jakarta.mail.MessagingException;
import m2i.ma.Brikol.User.dto.EmailRequest;
import m2i.ma.Brikol.User.dto.ResetPasswordRequest;
import m2i.ma.Brikol.User.dto.ResetPasswordToken;
import m2i.ma.Brikol.User.dto.ResetPasswordUID;
import org.springframework.http.ResponseEntity;

public interface ResetPasswordService {
    ResponseEntity<ResetPasswordUID> resetPasswordRequest(EmailRequest emailRequest) throws MessagingException;
    ResponseEntity<String> validateToken(ResetPasswordToken resetPasswordToken);
    ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest);
}
