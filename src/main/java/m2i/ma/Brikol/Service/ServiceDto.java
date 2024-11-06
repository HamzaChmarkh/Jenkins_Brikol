package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;

public class ServiceDto {
    private  Long id;
    private String titre;
    private String description;
    private Double prix;
    private Freelancer freelancerId;
    private Categorie categorieId;

    public ServiceDto() {
    }

    public ServiceDto(Long id, String titre, String description, Double prix, Freelancer freelancerId, Categorie categorieId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.freelancerId = freelancerId;
        this.categorieId = categorieId;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrix() {
        return prix;
    }

    public Freelancer getFreelancerId() {
        return freelancerId;
    }

    public Categorie getCategorieId() {
        return categorieId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setFreelancerId(Freelancer freelancerId) { this.freelancerId = freelancerId;}


    public void setCategorieId(Categorie categorieId) {
        this.categorieId = categorieId;
    }
}
