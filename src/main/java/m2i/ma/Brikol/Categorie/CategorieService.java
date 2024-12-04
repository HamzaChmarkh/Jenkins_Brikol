package m2i.ma.Brikol.Categorie;

import jakarta.transaction.Transactional;
import m2i.ma.Brikol.Exceptions.CategoryNotFoundException;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategorieService {

    private final CategorieRepository categorieRepository;

    private static final String CATEGORY_NULL = "Category is null";


    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public CategorieDto getCategorieDto(Categorie categorie) {
           if (categorie == null) {
               throw new ValidationException(CATEGORY_NULL);
        }
        return new CategorieDto(
                categorie.getTitreAr(),
                categorie.getTitreFr(),
                categorie.getTitreEn(),
                categorie.getId()
        );

    }

    public ResponseEntity<ResponseDto> createCategorie(Categorie categorie) {
        try {
            if (categorie == null) {
                throw new ValidationException(CATEGORY_NULL);
            }
            checkCategory(categorie);
            categorieRepository.save(categorie);
            return ResponseEntity.ok(new ResponseDto("Category created successfully", HttpStatus.OK.value()));
        } catch (Exception e) {
            throw new ValidationException("Error creating category");
        }

    }

    public ResponseEntity<ResponseDto> deleteCategorieById(Long id) {
        try {
            if (id == null) {
                throw new ValidationException("Category id is null");
            }

            if (!categorieRepository.existsById(id)) {
                throw new CategoryNotFoundException(CATEGORY_NULL);
            }

            categorieRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseDto("Category deleted successfully", HttpStatus.OK.value()));
        } catch (Exception e) {
            throw new ValidationException("Error deleting category");
        }


    }



    public ResponseEntity<ResponseDto> updateCategorie(Categorie categorie) {
        try {
            if (categorie == null) {
                throw new ValidationException(CATEGORY_NULL);
            }
            checkCategory(categorie);
            if (!categorieRepository.existsById(categorie.getId())) {
                throw new CategoryNotFoundException(CATEGORY_NULL);
            }
            categorieRepository.save(categorie);
            return ResponseEntity.ok(new ResponseDto("Category type updated successfully", HttpStatus.OK.value()));
        } catch (Exception e) {
            throw new ValidationException("Error updating category");
        }
    }
    public static void checkCategory(Categorie categorie) {
        if (categorie == null) {
            throw new ValidationException(CATEGORY_NULL);
        } else if (categorie.getTitreAr() == null) {
            throw new ValidationException("Arabic title is null");
        } else if (categorie.getTitreFr() == null) {
            throw new ValidationException("French title is null");
        } else if (categorie.getTitreEn() == null) {
            throw new ValidationException("English title is null");
        }
    }


 public ResponseEntity<CategorieDto> getCategorieById(Long id) {
        try {
            if (id == null) {
                throw new ValidationException("Category id is null");
            }

            Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NULL));
            return ResponseEntity.ok(getCategorieDto(categorie));
        } catch (Exception e) {
            throw new ValidationException("Error getting category");
        }
}



    public ResponseEntity<List<CategorieDto>> getAllCategories() {
        try {
            List<Categorie> categories = categorieRepository.findAll();
            if (categories.isEmpty()) {
                throw new CategoryNotFoundException("No categories found");
            }

            return ResponseEntity.ok(categories.stream().map(this::getCategorieDto).toList());
        } catch (Exception e) {
            throw new ValidationException("Error getting categories");
        }


    }
}
