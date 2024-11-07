package m2i.ma.Brikol.User.service;

import jakarta.mail.MessagingException;
import m2i.ma.Brikol.User.dto.*;
import org.springframework.http.ResponseEntity;


public interface AuthenticationService {

    ResponseEntity<JwtAuthenticationResponse> signIn(SignInRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    ResponseEntity<String> signUp(SignUpRequest signUpRequest) throws MessagingException;

    ResponseEntity<String> confirmToken(String token);
}
