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
    @Column(name = "titre_ar")
    private String titreAr;
    @Column(name = "titre_fr")
    private String titreFr;
    @Column(name = "titre_en")
    private String titreEn;



    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Service.class)
    private List<Service> services;


}
