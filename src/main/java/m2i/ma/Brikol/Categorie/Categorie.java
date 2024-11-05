package m2i.ma.Brikol.Categorie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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


    public Categorie(String type, List<Service> services, Service service) {
        this.type = type;
        this.services = services;
        this.services.add(service);
    }

}
