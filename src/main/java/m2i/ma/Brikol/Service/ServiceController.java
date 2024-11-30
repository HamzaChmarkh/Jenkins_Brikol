package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Freelancer.Freelancer;
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
        public ResponseEntity<List<ServiceDto>> getServiceByCategorie(@RequestBody Categorie categorie) {
         return   serviceLogic.getServiceByCategorie(categorie);
        }

        @GetMapping("/ByTitre")
        public ResponseEntity<List<ServiceDto>> getServiceByTitre(@RequestParam String titre) {
         return   serviceLogic.getServiceByTitre(titre);
        }

        @GetMapping("/ByPrix")
        public ResponseEntity<List<ServiceDto>> getServiceByPrix(@RequestParam Double prix) {
         return    serviceLogic.getServiceByPrix(prix);
        }

        @GetMapping("/ByFreelancer")
        public ResponseEntity<List<ServiceDto>> getServiceByFreelancer(@RequestBody Freelancer freelancer) {
         return   serviceLogic.getServiceByFreelancer(freelancer);
        }

    @PostMapping("/update")
        public ResponseEntity<ResponseDto> modfierTous(@RequestBody Service service, @RequestBody Categorie categorie) {
           return  serviceLogic.modfierTous(service, categorie);
        }

        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createService(@RequestBody Service service, @RequestBody Categorie categorie, @RequestBody Freelancer freelancer) {
            return serviceLogic.ajouterService(service, categorie, freelancer);

        }

        @PostMapping("/delete")
        public ResponseEntity<ResponseDto> deleteService(@RequestBody Service service) {
            return serviceLogic.supprimerService(service);
        }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteService(@PathVariable Long id) {
        return serviceLogic.supprimerService(id);
    }





}
