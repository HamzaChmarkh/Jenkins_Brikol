package m2i.ma.Brikol.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Categorie.CategorieDto;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Freelancer.FreelancerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
        private final ServiceLogic serviceLogic;
        @Autowired
        public ServiceController(ServiceLogic serviceLogic) {
            this.serviceLogic = serviceLogic;
        }

        @GetMapping("/{id}")
        public ResponseEntity<ServiceDto>  getServiceById(@PathVariable Long id) {
         return    serviceLogic.getServiceById(id);
        }

        @GetMapping("/ByCategorie")
        public ResponseEntity<List<ServiceDto>> getServiceByCategorie(@Valid @RequestBody CategorieDto categorieDto) {
            Categorie categorie = categorieDto.toCategorie();
         return   serviceLogic.getServiceByCategorie(categorie);
        }

        @GetMapping("/ByTitre/{titre}")
        public ResponseEntity<List<ServiceDto>> getServiceByTitre(@RequestParam String titre) {
         return   serviceLogic.getServiceByTitre(titre);
        }

        @GetMapping("/ByPrix/{prix}")
        public ResponseEntity<List<ServiceDto>> getServiceByPrix(@PositiveOrZero @Valid @RequestParam Double prix) {
         return    serviceLogic.getServiceByPrix(prix);
        }

        @GetMapping("/ByFreelancer")
        public ResponseEntity<List<ServiceDto>> getServiceByFreelancer(@Valid @RequestBody FreelancerDto freelancerDto) {
            Freelancer freelancer = freelancerDto.toFreelancer();
         return   serviceLogic.getServiceByFreelancer(freelancer);
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

        @PostMapping("/delete")
        public ResponseEntity<ResponseDto> deleteService(@Valid @RequestBody ServiceDto serviceDto) {
            Service service = serviceDto.toService();
            return serviceLogic.supprimerService(service);
        }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteService(@NotNull @Valid @PathVariable Long id) {
        return serviceLogic.supprimerService(id);
    }





}
