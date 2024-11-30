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
        if (categorie == null) {
            throw new ValidationException(CATEGORY_NULL);
        }
        categorieRepository.save(categorie);
        return ResponseEntity.ok(new ResponseDto("Category created successfully", HttpStatus.OK.value()));
    }

    public ResponseEntity<ResponseDto> deleteCategorieById(Long id) {
        if (id == null) {
            throw new ValidationException("Category id is null");
        }

        if (!categorieRepository.existsById(id)) {
            throw new CategoryNotFoundException(CATEGORY_NULL);
        }

        categorieRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseDto("Category deleted successfully", HttpStatus.OK.value()));
    }



    public ResponseEntity<ResponseDto> updateCategorie(Categorie categorie) {
        if (categorie == null) {
            throw new ValidationException(CATEGORY_NULL);
        }

        if (!categorieRepository.existsById(categorie.getId())) {
            throw new CategoryNotFoundException(CATEGORY_NULL);
        }

        categorieRepository.save(categorie);
        return ResponseEntity.ok(new ResponseDto("Category type updated successfully", HttpStatus.OK.value()));
    }



 public ResponseEntity<CategorieDto> getCategorieById(Long id) {
    if (id == null) {
        throw new ValidationException("Category id is null");
    }

     Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NULL));
    return ResponseEntity.ok(getCategorieDto(categorie));
}



    public ResponseEntity<List<CategorieDto>> getAllCategories() {
        List<Categorie> categories = categorieRepository.findAll();
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("No categories found");
        }

        return ResponseEntity.ok(categories.stream().map(this::getCategorieDto).toList());

    }
}
