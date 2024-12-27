package m2i.ma.Brikol.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import m2i.ma.Brikol.Categorie.CategorieRepository;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancer/service")
public class ServiceController {
        private final ServiceLogic serviceLogic;
        private final CategorieRepository categorieRepository;
        @Autowired
        public ServiceController(ServiceLogic serviceLogic, CategorieRepository categorieRepository) {
            this.serviceLogic = serviceLogic;
            this.categorieRepository = categorieRepository;
        }

        @GetMapping("/{id}")
        public ResponseEntity<ServiceDto>  getServiceById(@PathVariable Long id) {
         return    serviceLogic.getServiceById(id);
        }

        @GetMapping("/ByCategorie/{id}")
        public ResponseEntity<List<ServiceDto>> getServiceByCategorie(@Valid @PathVariable Long id) {

         return   serviceLogic.getServiceByCategorie(id);
        }



        @GetMapping("/ByPrix/{prix}")
        public ResponseEntity<List<ServiceDto>> getServiceByPrix(@PositiveOrZero @Valid @PathVariable Double prix) {
         return    serviceLogic.getServiceByPrix(prix);
        }

        @GetMapping("/ByIdFreelancer/{id}")
        public ResponseEntity<List<ServiceDto>> getServiceByFreelancer(@Valid @PathVariable Long id) {

         return   serviceLogic.getServiceByFreelancer(id);
        }

        @PostMapping("/update")
        public ResponseEntity<ResponseDto> modfierTous(@Valid @RequestBody ServiceDto serviceDto) {
            Service service = serviceDto.toService();
           return  serviceLogic.modfierTous(service);
        }
        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createService(@Valid @RequestBody ServiceDto serviceDto) {
            Service service = serviceDto.toService();
            return serviceLogic.ajouterService(service);

        }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteService(@NotNull @Valid @PathVariable Long id) {
        return serviceLogic.supprimerService(id);
    }





}
