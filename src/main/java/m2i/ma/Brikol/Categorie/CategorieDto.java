package m2i.ma.Brikol.Categorie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategorieDto {
    @NotNull(message = "id is required")
    private final Long id;
    @NotBlank(message = "titreAr is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "titreAr must contain only letters and numbers")
    private final String titreAr;
    @NotBlank(message = "titreFr is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "titreFr must contain only letters and numbers")
    private final String titreFr;
    @NotBlank(message = "titreEn is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "titreEn must contain only letters and numbers")
    private final String titreEn;

    public CategorieDto(String titreAr, String titreFr, String titreEn, Long id) {
        this.titreAr = titreAr;
        this.titreFr = titreFr;
        this.titreEn = titreEn;
        this.id = id;
    }


    public Categorie toCategorie() {
        return new Categorie(id,titreAr, titreFr, titreEn);
    }
}
