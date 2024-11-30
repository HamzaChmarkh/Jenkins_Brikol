package m2i.ma.Brikol.Categorie;

public class CategorieDto {
     private final Long id;
    private final String titreAr;
    private final String titreFr;
    private final String titreEn;

    public CategorieDto(String titreAr, String titreFr, String titreEn, Long id) {
        this.titreAr = titreAr;
        this.titreFr = titreFr;
        this.titreEn = titreEn;
        this.id = id;
    }


}
