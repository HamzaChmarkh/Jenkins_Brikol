package m2i.ma.Brikol.Categorie;

import m2i.ma.Brikol.Service.Service;

import java.util.List;

public class CategorieDto {
     private final Long id;

    private final String type;
    private final  List<Service> services;

    public CategorieDto(Long id, String type, List<Service> services) {
        this.id = id;
        this.type = type;
        this.services = services;
    }

}
