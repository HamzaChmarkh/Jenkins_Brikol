package m2i.ma.Brikol.User.resetpassword;

import jakarta.persistence.*;
import lombok.*;
import m2i.ma.Brikol.User.Utilisateur;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token; // Store the 6-digit token here

    @ManyToOne
    @JoinColumn(nullable = false, name = "utilisateur_id")
    private Utilisateur user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column(nullable = false)
    private boolean used = false;

    @Column(nullable = false)
    private boolean isValidated = false;

    private String uid;

}
