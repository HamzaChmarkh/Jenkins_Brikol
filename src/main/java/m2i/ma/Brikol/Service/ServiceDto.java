package m2i.ma.Brikol.Service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import m2i.ma.Brikol.Categorie.Categorie;

@Setter
@Getter
public class ServiceDto {

    private  Long id;
    @NotBlank(message = "titre is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$",message = "titre must contain only letters and numbers")
    private String titre;
    @NotBlank(message = "description is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$",message = "description must contain only letters and numbers")
    private String description;
    @NotNull(message = "prix is required")
    @PositiveOrZero(message = "prix must be positive")
    private Double prix;
    @NotBlank(message = "pathImage is required")
    private String pathImage;
    @NotNull(message = "freelancerId is required")
    private Long freelancerId;
    @NotNull(message = "categorieId is required")
    private Long categorie;

    public ServiceDto(Long id, String titre, String description, Double prix,String pathImage, Long freelancerId, Long categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.pathImage = pathImage;
        this.freelancerId = freelancerId;
        this.categorie= categorie;
    }



    public Service toService(){
        Service service = new Service();
        service.setId(this.id);
        service.setTitre(this.titre);
        service.setDescription(this.description);
        service.setPrix(this.prix);
        service.setPathImage(this.pathImage);
        service.setIdFreelancer(this.freelancerId);
        service.setCategorie(this.categorie);
        return service;
    }


}
