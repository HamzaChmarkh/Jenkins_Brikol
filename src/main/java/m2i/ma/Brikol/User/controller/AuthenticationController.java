package m2i.ma.Brikol.User.controller;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.User.dto.*;
import m2i.ma.Brikol.User.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInRequest signinRequest ){
        return authenticationService.signIn(signinRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest ){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/check_email")
    public ResponseEntity<?> checkEmail(@RequestBody EmailRequest emailRequest){
        return authenticationService.checkEmail(emailRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request ) throws MessagingException {
        return authenticationService.signUp(request);
    }

    @GetMapping("/registration/confirm")
    public ResponseEntity<String> confirmToken(@RequestParam String token){
        return authenticationService.confirmToken(token);
    }


}
