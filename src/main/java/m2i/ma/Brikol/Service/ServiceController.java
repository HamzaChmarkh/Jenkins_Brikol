package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        public ResponseEntity<ServiceDto>  getServiceByCategorie(@RequestBody  Categorie categorie) {
         return   serviceLogic.getServiceByCategorie(categorie);
        }

        @GetMapping("/ByTitre")
        public ResponseEntity<ServiceDto>  getServiceByTitre(@RequestParam String titre) {
         return   serviceLogic.getServiceByTitre(titre);
        }

        @GetMapping("/ByPrix")
        public ResponseEntity<ServiceDto>  getServiceByPrix(@RequestParam  Double prix) {
         return    serviceLogic.getServiceByPrix(prix);
        }

        @GetMapping("/ByFreelancer")
        public ResponseEntity<ServiceDto>  getServiceByFreelancer(@RequestBody Freelancer freelancer) {
         return   serviceLogic.getServiceByFreelancer(freelancer);
        }





    

        @PutMapping("/modfierTous")
        public ResponseEntity<ResponseDto> modfierTous(@RequestBody Service service, @RequestBody Categorie categorie) {
           return  serviceLogic.modfierTous(service, categorie);
        }

        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createService(@RequestBody  Service service, @RequestBody Categorie categorie) {
            return serviceLogic.ajouterService(service, categorie);

        }

        @PostMapping("/delete")
        public ResponseEntity<ResponseDto> deleteService(@RequestBody Service service) {
            return serviceLogic.supprimerService(service);
        }



}
