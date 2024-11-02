package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceController {
        private final ServiceLogic serviceLogic;
        @Autowired
        public ServiceController(ServiceLogic serviceLogic) {
            this.serviceLogic = serviceLogic;
        }
        @GetMapping("/id")
        public Service getServiceById(Long id) {
         return    serviceLogic.getSreficeById(id);
        }
        @GetMapping("/ByCategorie")
        public Service getServiceByCategorie(Categorie categorie) {
         return    serviceLogic.getServiceByCategorie(categorie);
        }
        @GetMapping("/ByTitre")
        public Service getServiceByTitre(String titre) {
         return    serviceLogic.getServiceByTitre(titre);
        }
        @GetMapping("/ByPrix")
        public Service getServiceByPrix(Double prix) {
         return    serviceLogic.getServiceByPrix(prix);
        }
        @GetMapping("/ByFreelancer")
        public Service getServiceByFreelancer(Freelancer freelancer) {
         return    serviceLogic.getServiceByFreelancer(freelancer);
        }
        @GetMapping("/modifierPrix")
        public void modifierPrix(Double prix) {
            serviceLogic.modifierPrix(prix);
        }
        @GetMapping("/modifierTitre")
        public void modiferTitre(String titre) {
            serviceLogic.modiferTitre(titre);
        }
        @GetMapping("/modifierCategory")
        public void modifierCategory(Service service, Categorie categorie) {
            serviceLogic.modifierCategory(service, categorie);
        }
        @GetMapping("/modifierFreelancer")
        public void modifierFreelancer(Service service, Freelancer freelancer) {
            serviceLogic.modifierFreelancer(service, freelancer);
        }
        @GetMapping("/modfierTous")
        public void modfierTous(Service service, Categorie categorie, String titre, Double prix, String description) {
            serviceLogic.modfierTous(service, categorie, titre, prix, description);
        }


}
