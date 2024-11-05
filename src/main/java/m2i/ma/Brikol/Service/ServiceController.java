package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
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
        public ServiceDto getServiceById(@PathVariable Long id) {
         return    serviceLogic.getServiceById(id);
        }

        @GetMapping("/ByCategorie")
        public ServiceDto getServiceByCategorie(@RequestBody  Categorie categorie) {
         return   serviceLogic.getServiceByCategorie(categorie);
        }

        @GetMapping("/ByTitre")
        public ServiceDto getServiceByTitre(@RequestParam String titre) {
         return   serviceLogic.getServiceByTitre(titre);
        }

        @GetMapping("/ByPrix")
        public ServiceDto getServiceByPrix(@RequestParam  Double prix) {
         return    serviceLogic.getServiceByPrix(prix);
        }

        @GetMapping("/ByFreelancer")
        public ServiceDto getServiceByFreelancer(@RequestBody Freelancer freelancer) {
         return   serviceLogic.getServiceByFreelancer(freelancer);
        }

        @PutMapping("/modifierPrix")
        public ResponseEntity<String> modifierPrix(@RequestParam Service service,@RequestParam Double prix) {
           return serviceLogic.modifierPrix(service,prix);
        }
        @PutMapping("/modifierDescription")
        public ResponseEntity<String> modifierDescription(@RequestParam Service service,@RequestParam String description) {
            return serviceLogic.modifierDescription(service,description);
        }

        @PutMapping("/modifierTitre")
        public ResponseEntity<String> modiferTitre(@RequestParam Service service, @RequestParam String titre) {

            return serviceLogic.modiferTitre(service,titre);
        }

        @PutMapping("/modifierCategory")
        public ResponseEntity<String> modifierCategory(@RequestBody Service service,@RequestBody Categorie categorie) {
            return serviceLogic.modifierCategorie(service, categorie);
        }

    

        @PutMapping("/modfierTous")
        public ResponseEntity<String> modfierTous(@RequestBody Service service,@RequestBody Categorie categorie) {
           return  serviceLogic.modfierTous(service, categorie);
        }

        @PostMapping("/create")
        public ResponseEntity<String> createService(@RequestBody  Service service, @RequestBody Categorie categorie) {
            return serviceLogic.ajouterService(service, categorie);

        }

        @PostMapping("/delete")
        public ResponseEntity<String> deleteService(@RequestBody Service service) {
            return serviceLogic.supprimerService(service);
        }



}
