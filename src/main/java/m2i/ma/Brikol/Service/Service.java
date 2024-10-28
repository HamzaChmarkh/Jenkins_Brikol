package m2i.ma.Brikol.Service;

import jakarta.persistence.*;
import lombok.*;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;

@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "freelancer_id")
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public void afficherDetails() {
        // Logic to display service details
    }

  public void supprimerService(Service service) {
        Categorie categorie = this.categorie;
        service.setFreelancer(null); // Remove the link to this freelancer
        service.setCategorie(null); // Remove the link to Catego
        categorie.supprimerService(service);
    }

    public void modifierService(Service service) {
        service.setFreelancer(freelancer);
        service.setCategorie(categorie);

    }

}
