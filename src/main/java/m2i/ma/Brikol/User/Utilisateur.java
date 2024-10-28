package m2i.ma.Brikol.User;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String username;
    private String email;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "email_verifier")
    private boolean emailVerifier;

    public void sInscrire() {
        // Logic for signing up a user
    }

    public void seConnecter() {
        // Logic for user login
    }
    public void reinitialiserMotDePasse(Utilisateur user) {
        // Logic to reset a user's password
    }
    public void modifierProfil(String nom, String username, String email) {
       //logic to modify profile info
    }

    public void verifierEmail() {
        // Logic for verifying email

    }


}
