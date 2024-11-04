package m2i.ma.Brikol.User.confirmation;

import jakarta.persistence.*;
import lombok.*;
import m2i.ma.Brikol.User.Utilisateur;

import java.time.LocalDateTime;

@Entity
@Table(name = "confirmation_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
