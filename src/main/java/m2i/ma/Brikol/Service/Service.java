package m2i.ma.Brikol.Service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2i.ma.Brikol.Categorie.Categorie;

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

    @PrimaryKeyJoinColumn(name = "idfreelancer")
    private Long Idfreelancer;

    @PrimaryKeyJoinColumn(name = "idcategorie")
    private Categorie Idcategorie;
    
    public Object toServiceDto(){
        return new ServiceDto(this.id, this.titre, this.description, this.prix, this.pathImage, this.Idfreelancer, this.Idcategorie);
    }

}