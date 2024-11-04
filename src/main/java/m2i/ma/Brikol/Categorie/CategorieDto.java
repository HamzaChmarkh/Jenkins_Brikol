package m2i.ma.Brikol.Categorie;

import m2i.ma.Brikol.Service.Service;

import java.util.List;

public class CategorieDto {
     private Long id;

    private String type;
    private List<Service> services;

    public CategorieDto(Long id, String type, List<Service> services) {
        this.id = id;
        this.type = type;
        this.services = services;
    }

}
