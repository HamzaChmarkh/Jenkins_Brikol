package m2i.ma.Brikol.Service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Column(name = "titre")
    private String titre;
    @Column(name = "description")
    private String description;
    @Column(name = "prix")
    private Double prix;
    @Column(name = "path_image")
    String pathImage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, targetEntity = Freelancer.class)
    @JoinColumn(name = "freelancer_id")
    private Freelancer freelancer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, targetEntity = Categorie.class)
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;


}