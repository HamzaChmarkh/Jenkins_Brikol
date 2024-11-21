package m2i.ma.Brikol.Service;

import lombok.Getter;
import lombok.Setter;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;

@Setter
@Getter
public class ServiceDto {
    private  Long id;
    private String titre;
    private String description;
    private Double prix;
    private Freelancer freelancerId;
    private Categorie categorieId;

    public ServiceDto(Long id, String titre, String description, Double prix, Freelancer freelancerId, Categorie categorieId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.freelancerId = freelancerId;
        this.categorieId = categorieId;
    }


}
