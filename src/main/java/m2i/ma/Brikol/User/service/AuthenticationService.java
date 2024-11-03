package m2i.ma.Brikol.User.service;

import jakarta.mail.MessagingException;
import m2i.ma.Brikol.User.dto.*;
import org.springframework.http.ResponseEntity;


public interface AuthenticationService {

    public ResponseEntity<JwtAuthenticationResponse> signIn(SignInRequest signinRequest);

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    public ResponseEntity<String> signUp(SignUpRequest signUpRequest) throws MessagingException;

    ResponseEntity<String> confirmToken(String token);
}
