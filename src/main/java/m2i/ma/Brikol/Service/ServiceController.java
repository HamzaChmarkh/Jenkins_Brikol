package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.beans.factory.annotation.Autowired;
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
        public Service getServiceById(@PathVariable Long id) {
         return    serviceLogic.getServiceById(id);
        }

        @GetMapping("/ByCategorie")
        public Service getServiceByCategorie(@RequestBody  Categorie categorie) {
         return    serviceLogic.getServiceByCategorie(categorie);
        }

        @GetMapping("/ByTitre")
        public Service getServiceByTitre(@RequestParam String titre) {
         return    serviceLogic.getServiceByTitre(titre);
        }

        @GetMapping("/ByPrix")
        public Service getServiceByPrix(@RequestParam  Double prix) {
         return    serviceLogic.getServiceByPrix(prix);
        }

        @GetMapping("/ByFreelancer")
        public Service getServiceByFreelancer(@RequestBody Freelancer freelancer) {
         return    serviceLogic.getServiceByFreelancer(freelancer);
        }

        @GetMapping("/modifierPrix")
        public void modifierPrix(@RequestParam Double prix) {
            serviceLogic.modifierPrix(prix);
        }

        @GetMapping("/modifierTitre")
        public void modiferTitre(@RequestParam String titre) {
            serviceLogic.modiferTitre(titre);
        }

        @GetMapping("/modifierCategory")
        public void modifierCategory(@RequestBody Service service,@RequestBody Categorie categorie) {
            serviceLogic.modifierCategory(service, categorie);
        }

        @GetMapping("/modifierFreelancer")
        public void modifierFreelancer(@RequestBody Service service,@RequestBody Freelancer freelancer) {
            serviceLogic.modifierFreelancer(service, freelancer);
        }

        @GetMapping("/modfierTous")
        public void modfierTous(@RequestBody Service service,@RequestBody Categorie categorie) {
            serviceLogic.modfierTous(service, categorie);
        }

        @PostMapping("/createService")
        public void createService(@RequestBody  Service service,@RequestBody Categorie categorie) {
             serviceLogic.ajouterService(service, categorie);
            //TODO :change return for status code reponse
        }

        @GetMapping("/deleteService")
        public void deleteService(@RequestBody Service service) {
            serviceLogic.supprimerService(service);
        }



}
