package m2i.ma.Brikol.User.email;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmailConfirmation(String toEmail, String confirmationUrl, String username) throws MessagingException;
    void sendEmailResetPassword(String toEmail, String token) throws MessagingException;
}
