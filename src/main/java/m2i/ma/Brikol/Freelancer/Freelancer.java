package m2i.ma.Brikol.Freelancer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;

import java.util.List;

@Entity
@Table(name = "freelancer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Freelancer extends Utilisateur {

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Service> servicesProposes;

    public Freelancer(String nom, String email, Role role, String motDePasse) {
        super(nom, email, role, motDePasse);
    }

    public Freelancer(String nom, String email, Role role, String motDePasse, List<Service> servicesProposes) {
        super(nom, email, role, motDePasse);
        this.servicesProposes = servicesProposes;
    }


}
