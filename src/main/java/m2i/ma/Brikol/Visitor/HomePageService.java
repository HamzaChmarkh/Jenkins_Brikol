package m2i.ma.Brikol.Visitor;

import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.Service.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class HomePageService {

    private final ServiceRepository serviceRepository;
    private final JavaMailSender mailSender;
    private final ContactMessageRepo contactMessageRepo;

    @Autowired
    public HomePageService(ServiceRepository serviceRepository, JavaMailSender mailSender, ContactMessageRepo contactMessageRepo) {
        this.serviceRepository = serviceRepository;
        this.mailSender = mailSender;
        this.contactMessageRepo = contactMessageRepo;
    }

    public ResponseEntity<List<Service>> getAllAnnouncements() {
        List<Service> announcements = serviceRepository.findAll();
        return ResponseEntity.ok(announcements);
    }

    public ResponseEntity<ResponseDto> sendContactMessage(String name, String email, String message) {
        try {
            ContactMessage contactMessage = new ContactMessage();
            contactMessage.setName(name);
            contactMessage.setEmail(email);
            contactMessage.setMessage(message);

            contactMessageRepo.save(contactMessage);

            // Send email notification
            sendEmail(name, email, message);

            ResponseDto responseDto = new ResponseDto("Contact message sent successfully!", 200);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            ResponseDto errorResponse = new ResponseDto("Failed to send contact message: " + e.getMessage(), 500);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    private void sendEmail(String name, String email, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("brikool.team@gmail.com"); // Change to your email to receive messages
        mailMessage.setSubject("New Contact Message from " + name);
        mailMessage.setText("You have received a new message:\n\n" +
                "From: " + name + "\n" +
                "Email: " + email + "\n\n" +
                "Message:\n" + message);

        mailSender.send(mailMessage);
    }
}
