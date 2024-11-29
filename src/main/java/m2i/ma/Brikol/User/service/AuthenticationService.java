package m2i.ma.Brikol.User.service;

import jakarta.mail.MessagingException;
import m2i.ma.Brikol.User.dto.*;
import org.springframework.http.ResponseEntity;


public interface AuthenticationService {

    ResponseEntity<SignInResponse> signIn(SignInRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    ResponseEntity<?> checkEmail(EmailRequest emailRequest);
    ResponseEntity<String> signUp(SignUpRequest signUpRequest) throws MessagingException;

    ResponseEntity<String> confirmToken(String token);

}
