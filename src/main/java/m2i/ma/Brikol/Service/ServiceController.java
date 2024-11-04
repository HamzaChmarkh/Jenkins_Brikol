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

        @GetMapping("/modifierPrix")
        public ResponseEntity<String> modifierPrix(@RequestParam Service service,@RequestParam Double prix) {
           return serviceLogic.modifierPrix(service,prix);
        }

        @GetMapping("/modifierTitre")
        public ResponseEntity<String> modiferTitre(@RequestParam Service service, @RequestParam String titre) {

            return serviceLogic.modiferTitre(service,titre);
        }

        @GetMapping("/modifierCategory")
        public ResponseEntity<String> modifierCategory(@RequestBody Service service,@RequestBody Categorie categorie) {
            return serviceLogic.modifierCategory(service, categorie);
        }

    

        @GetMapping("/modfierTous")
        public ResponseEntity<String> modfierTous(@RequestBody Service service,@RequestBody Categorie categorie) {
           return  serviceLogic.modfierTous(service, categorie);
        }

        @PostMapping("/createService")
        public ResponseEntity<String> createService(@RequestBody  Service service, @RequestBody Categorie categorie) {
            return serviceLogic.ajouterService(service, categorie);

        }

        @GetMapping("/deleteService")
        public ResponseEntity<String> deleteService(@RequestBody Service service) {
            return serviceLogic.supprimerService(service);
        }



}
