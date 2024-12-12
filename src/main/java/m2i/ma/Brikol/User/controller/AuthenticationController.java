package m2i.ma.Brikol.User.controller;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.User.dto.*;
import m2i.ma.Brikol.User.resetpassword.ResetPasswordService;
import m2i.ma.Brikol.User.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ResetPasswordService resetPasswordService;

    @PostMapping("/login")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signinRequest ){
        return authenticationService.signIn(signinRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<SignInResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest ){
        return authenticationService.refreshToken(refreshTokenRequest);
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

    @PostMapping("/reset_password/request")
    public ResponseEntity<ResetPasswordUID> resetPasswordRequest(@RequestBody EmailRequest emailRequest) throws MessagingException {
        return resetPasswordService.resetPasswordRequest(emailRequest);
    }
    @PostMapping("/reset_password/confirm")
    public ResponseEntity<String> checkResetPasswordToken(@RequestBody ResetPasswordToken resetPasswordTokenRequest){
        return resetPasswordService.validateToken(resetPasswordTokenRequest);
    }
    @PatchMapping("/reset_password/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        return resetPasswordService.resetPassword(resetPasswordRequest);
    }


}
