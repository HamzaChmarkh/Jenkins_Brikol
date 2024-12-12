package m2i.ma.Brikol.User.resetpassword;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.UtilisateurRepository;
import m2i.ma.Brikol.User.dto.EmailRequest;
import m2i.ma.Brikol.User.dto.ResetPasswordRequest;
import m2i.ma.Brikol.User.dto.ResetPasswordToken;
import m2i.ma.Brikol.User.dto.ResetPasswordUID;
import m2i.ma.Brikol.User.email.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final UtilisateurRepository utilisateurRepository;
    private final ResetPasswordRepository resetPasswordRepository;
    private final EmailService emailService;


    @Override
    public ResponseEntity<ResetPasswordUID> resetPasswordRequest(EmailRequest emailRequest) throws MessagingException {
        Utilisateur user  = utilisateurRepository.findByEmail(emailRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        String token;

        do {
            token = ResetPasswordUtil.generateNumericToken();
        } while (resetPasswordRepository.existsByToken(token));
        String uid = UUID.randomUUID().toString();
        PasswordResetToken resetToken = PasswordResetToken.builder()
            .token(token)
            .user(user)
            .expiryDate(LocalDateTime.now().plusMinutes(3))
            .uid(uid)
            .build();

        resetPasswordRepository.findByUser(user).ifPresent(resetPasswordRepository::delete);

        resetPasswordRepository.save(resetToken);

        emailService.sendEmailResetPassword(emailRequest.getEmail(), token);

        return new ResponseEntity<>(new ResetPasswordUID(uid), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<String> validateToken(ResetPasswordToken resetPasswordToken){
        Optional<PasswordResetToken> token = resetPasswordRepository.findByToken(resetPasswordToken.getToken());
        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        if (token.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expired");
        }

        if(!token.get().getUid().equals(resetPasswordToken.getUid())){
            return ResponseEntity.badRequest().body("Invalid token");
        }

        PasswordResetToken passwordResetToken = token.get();
        passwordResetToken.setValidated(true);
        resetPasswordRepository.save(passwordResetToken);

        return ResponseEntity.ok("Token is valid");
    }

    @Override
    public ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Optional<PasswordResetToken> token = resetPasswordRepository.findByUid(resetPasswordRequest.getUid());

        if (token.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        if (token.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expired");
        }

        if(!token.get().isValidated()){
            return ResponseEntity.badRequest().body("Token not validated");
        }

        Utilisateur user = token.get().getUser();


        user.setMotDePasse(new BCryptPasswordEncoder(12).encode(resetPasswordRequest.getNewPassword()));
        utilisateurRepository.save(user);

        token.get().setUsed(true);
        resetPasswordRepository.save(token.get());

        return ResponseEntity.ok("Password reset successfully");
    }

}
