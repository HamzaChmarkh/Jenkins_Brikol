package m2i.ma.Brikol.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String username;
    private String email;

    @Column(insertable=false, updatable=false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "email_verifier")
    private boolean emailVerifier;

    private String image;

    public Utilisateur(String nom, String email, Role role, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.role = role;
        this.motDePasse = motDePasse;
    }

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



    @Override
    public String getPassword() {
        return motDePasse;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
