package m2i.ma.Brikol.User.service;

import jakarta.mail.MessagingException;
import m2i.ma.Brikol.User.dto.*;
import org.springframework.http.ResponseEntity;


public interface AuthenticationService {

    ResponseEntity<?> signIn(SignInRequest signinRequest);

    ResponseEntity<SignInResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);

    ResponseEntity<?> checkEmail(EmailRequest emailRequest);
    ResponseEntity<String> signUp(SignUpRequest signUpRequest) throws MessagingException;

    ResponseEntity<String> confirmToken(String token);

}
