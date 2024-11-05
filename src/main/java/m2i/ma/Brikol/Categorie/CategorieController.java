package m2i.ma.Brikol.Categorie;

import m2i.ma.Brikol.Service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }


@PostMapping("/addCategorie")
    public ResponseEntity<String> addCategorie(@RequestBody Categorie categorie) {
        return categorieService.createCategorie(categorie);
    }

    @DeleteMapping("/delete/{type}")
    public ResponseEntity<String> deleteCategorieByname(@PathVariable String type) {
        return categorieService.deleteCategorieBytype(type);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategorie(@PathVariable Long id) {
        return categorieService.deleteCategorieById(id);
    }

    @GetMapping("/get/{id}")
    public CategorieDto getCategorieById(@PathVariable Long id) {
        return categorieService.getCategorieById(id);
    }

    @GetMapping("/get/{type}")
    public CategorieDto getCategorieBytype(@PathVariable String type) {
        return categorieService.getCategorieByType(type);
    }
    @DeleteMapping



    @GetMapping("/updateAll")
    public ResponseEntity<String> updateCategorie(Categorie categorie) {
        return categorieService.updateCategorie(categorie);
    }

    @GetMapping("/updateType")
    public ResponseEntity<String> updateCategorieType(Categorie categorie) {
        return categorieService.updateCategorieType(categorie);
    }

    @PostMapping("/updateServices")
    public ResponseEntity<String> updateCategorieServices(@RequestBody Categorie categorie, @RequestBody List<Service> services) {
        return categorieService.updateCategorieServices(categorie, services);
    }

    @GetMapping("/allCategories")
    public List<CategorieDto> getAllCategories() {
        return categorieService.GetallCategories();
    }


}
