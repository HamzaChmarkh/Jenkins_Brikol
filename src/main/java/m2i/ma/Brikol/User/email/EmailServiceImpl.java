package m2i.ma.Brikol.User.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    public void sendEmailConfirmation(String toEmail, String token, String username) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String confirmationUrl = "http://localhost:8080/api/auth/registration/confirm?token=" + token;
        // Set up Thymeleaf context with variables
        Context context = new Context();
        context.setVariable("userName", username);
        context.setVariable("confirmationUrl", confirmationUrl);

        // Render the template
        String htmlContent = templateEngine.process("confirmation-email", context);

        helper.setText(htmlContent, true); // Enable HTML
        helper.setTo(toEmail);
        helper.setSubject("Email Confirmation");
        helper.setFrom("your-email@example.com");

        mailSender.send(mimeMessage);
    }
}
