package m2i.ma.Brikol.Freelancer;

import jakarta.persistence.*;
import lombok.*;
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

    private String specialisation;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Service> servicesProposes;

    public Freelancer(String nom, String email, Role role, String motDePasse) {
        super(nom, email, role, motDePasse);
    }
    public void ajouterService(Service service, Categorie categorie) {
        servicesProposes.add(service);
        service.setFreelancer(this); // Set this freelancer for the service
        service.setCategorie(categorie);
    }

    public void modifierService(Service service) {
        // Logic to modify the service
    }

    public void supprimerService(Service service) {
        servicesProposes.remove(service);
        service.supprimerService(service);
    }
}
