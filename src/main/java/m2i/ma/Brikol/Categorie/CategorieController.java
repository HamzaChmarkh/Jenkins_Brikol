package m2i.ma.Brikol.Categorie;

import m2i.ma.Brikol.Service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    public ResponseEntity<String> addCategorie(@RequestBody Categorie categorie) {
        return categorieService.createCategorie(categorie);
    }

    @GetMapping("/delete/{type}")
    public ResponseEntity<String> deleteCategorieByname(@PathVariable String type) {
        return categorieService.deleteCategorieBytype(type);
    }

    @GetMapping("/update")
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

    @GetMapping("/categories")
    public List<CategorieDto> getAllCategories() {
        return categorieService.GetallCategories();
    }


}
