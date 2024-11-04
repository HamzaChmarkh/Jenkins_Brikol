package m2i.ma.Brikol.Categorie;

import m2i.ma.Brikol.Service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    
    Categorie findByType(String type);

    void updateCategorieByType(String type);

    void updateCategorieServices(Categorie categorie,List<Service> services);

    void updateCategorieType(Categorie categorie, String type);
}
