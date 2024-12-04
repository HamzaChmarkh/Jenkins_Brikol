package m2i.ma.Brikol.Categorie;

import jakarta.validation.Valid;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorie")
public class CategorieController {
    private final CategorieService categorieService;
  @Autowired
    public CategorieController(CategorieService categorieService) {
      this.categorieService = categorieService;
    }


    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addCategorie(@Valid @RequestBody CategorieDto categorieDTO) {
        Categorie categorie = categorieDTO.toCategorie();
        return categorieService.createCategorie(categorie);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateCategorie(@RequestBody CategorieDto categorieDto) {
        Categorie categorie = categorieDto.toCategorie();
        return categorieService.updateCategorie(categorie);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteCategorie(@PathVariable Long id) {
        return categorieService.deleteCategorieById(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategorieDto> getCategorieById(@PathVariable Long id) {
        return categorieService.getCategorieById(id);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CategorieDto>> getAllCategories() {

      return categorieService.getAllCategories();
    }


}
