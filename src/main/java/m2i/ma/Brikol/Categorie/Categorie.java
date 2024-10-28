package m2i.ma.Brikol.Categorie;

import jakarta.persistence.*;
import lombok.*;
import m2i.ma.Brikol.Service.Service;

import java.util.List;

@Entity
@Table(name = "categorie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Service> services;

    public void ajouterService(Service service) {
        services.add(service);
        service.setCategorie(this); // Set this category for the service
    }

    public void supprimerService(Service service) {
        services.remove(service);
        service.setCategorie(null); // Remove the link to this category
    }
}
