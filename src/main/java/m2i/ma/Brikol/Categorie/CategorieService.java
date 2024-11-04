package m2i.ma.Brikol.Categorie;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class CategorieService {


    private CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public CategorieDto getCategorieDto(Categorie categorie) {
        return new CategorieDto(
                categorie.getId(),
                categorie.getType(),
                categorie.getServices()
        );
    }

    public ResponseEntity<String> createCategorie(Categorie categorie) {
        try {
            categorieRepository.save(categorie);
            return ResponseEntity.ok("Category created successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while creating the category", e);
        }

    }

    public ResponseEntity<String> deleteCategorie(Long id) {
        try {
            categorieRepository.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the category", e);
        }
    }

    public Boolean updateCategorieByType(Categorie categorie) {
        try {
            categorieRepository.updateCategorieByType(categorie.getType());
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the category type", e);
        }
    }
    public Boolean updateCategorieServices(Categorie categorie) {
        try {
            categorieRepository.updateCategorieServices(categorie,categorie.getServices());
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the category services", e);
        }
    }

 public Boolean updateCategorieType(Categorie categorie, String type){
        try {
            categorieRepository.updateCategorieType(categorie, type);
            return Boolean.TRUE;
        }catch(Exception e){
            throw new ServiceException("An error occurred while updating the category type", e);
        }
 }

    public CategorieDto getCategorieByType(String type) {
        Categorie categorie = categorieRepository.findByType(type);
        return getCategorieDto(categorie);
    }
    public CategorieDto getCategorieById(Long id) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        return getCategorieDto(categorie);
    }



}




