package m2i.ma.Brikol.Categorie;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static m2i.ma.Brikol.Freelancer.FreelancerService.checkNull;

@Service

public class CategorieService {


    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public CategorieDto getCategorieDto(Categorie categorie) {
        try {
            checkNull(categorie, "Category is null");
            return new CategorieDto(
                    categorie.getId(),
                    categorie.getType(),
                    categorie.getServices()
            );
        } catch (Exception e) {
            throw new ServiceException("An error occurred while getting category dto", e);
        }

    }

    public ResponseEntity<String> createCategorie(Categorie categorie) {
      try {
            checkNull(categorie, "Category is null");
            categorieRepository.save(categorie);
            return ResponseEntity.ok("Category created successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while creating the category", e);
        }

    }

    public ResponseEntity<String> deleteCategorieById(Long id) {
        try {
            checkNull(id, "Category id is null");
            categorieRepository.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the category", e);
        }
    }

    public ResponseEntity<String> updateCategorieType(Categorie categorie) {
        try {
            checkNull(categorie, "Category is null");
            categorieRepository.updateCategorieType(categorie, categorie.getType());
            return ResponseEntity.ok("Category type updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the category type", e);
        }
    }

    public ResponseEntity<String> updateCategorieServices(Categorie categorie, List<m2i.ma.Brikol.Service.Service> services) {
        try {
            checkNull(categorie, "Category is null");
            checkNull(services, "Services is null");
            categorieRepository.updateCategorieServices(categorie, services);
            return ResponseEntity.ok("Category services updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the category services", e);
        }
    }

    public ResponseEntity<String> updateCategorie(Categorie categorie) {
        try {
            checkNull(categorie, "Category is null");
            categorieRepository.updateCategorie(categorie);
            return ResponseEntity.ok("Category updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the category", e);
        }
    }

    public CategorieDto getCategorieByType(String type) {
        try {
            checkNull(type, "Category type is null");
               Categorie categorie = categorieRepository.findByType(type);
        return getCategorieDto(categorie);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while getting category by type", e);
        }

    }
    public CategorieDto getCategorieById(Long id) {

            checkNull(id, "Category id is null");
            Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
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
            checkNull(type, "Category type is null");
            categorieRepository.deleteCategoriesByType(type);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the category", e);
        }
    }
}





