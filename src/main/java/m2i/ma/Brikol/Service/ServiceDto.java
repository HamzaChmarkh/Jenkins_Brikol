package m2i.ma.Brikol.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;

@Setter
@Getter
public class ServiceDto {
    @NotNull(message = "id is required")
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
    private Freelancer freelancerId;
    @NotNull(message = "categorieId is required")
    private Categorie categorieId;

    public ServiceDto(Long id, String titre, String description, Double prix,String pathImage, Freelancer freelancerId, Categorie categorieId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.pathImage = pathImage;
        this.freelancerId = freelancerId;
        this.categorieId = categorieId;
    }



    public Service toService(){
        return new Service(id,titre,description,prix,pathImage,freelancerId,categorieId);
    }


}
