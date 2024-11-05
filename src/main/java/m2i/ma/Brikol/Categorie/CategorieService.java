package m2i.ma.Brikol.Categorie;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategorieService {


    private final CategorieRepository categorieRepository;

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

    public ResponseEntity<String> updateCategorieType(Categorie categorie) {
        try {
            categorieRepository.updateCategorieType(categorie, categorie.getType());
            return ResponseEntity.ok("Category type updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the category type", e);
        }
    }

    public ResponseEntity<String> updateCategorieServices(Categorie categorie, List<m2i.ma.Brikol.Service.Service> services) {
        try {
            categorieRepository.updateCategorieServices(categorie, services);
            return ResponseEntity.ok("Category services updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the category services", e);
        }
    }

    public ResponseEntity<String> updateCategorie(Categorie categorie) {
        try {
            categorieRepository.updateCategorie(categorie);
            return ResponseEntity.ok("Category updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the category", e);
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

    public List<CategorieDto> GetallCategories() {
        try {
            return categorieRepository.findAll().stream().map(this::getCategorieDto).toList();
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching all categories", e);
        }

    }


    public ResponseEntity<String> deleteCategorieBytype(String type) {
        try {
            categorieRepository.deleteCategoriesByType(type);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the category", e);
        }
    }
}





