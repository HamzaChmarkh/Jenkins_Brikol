package m2i.ma.Brikol.Service;

import jakarta.persistence.EntityNotFoundException;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Freelancer.FreelancerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Service
public class ServiceLogic {


    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceLogic( ServiceRepository serviceRepository) {

        this.serviceRepository = serviceRepository;
    }

    public ResponseEntity<String> modfierTous(Service service, Categorie categorie) {
        try {
            FreelancerService.checkNull(service, "Service is null");
            FreelancerService.checkNull(categorie, "Category is null");
        serviceRepository.updateServiceCategorie(categorie);
        serviceRepository.updateServiceTitre(service.getTitre());
        serviceRepository.updateServicePrix(service.getPrix());
        serviceRepository.updateServiceDescription(service.getDescription());

            return ResponseEntity.ok("All service fields updated successfully");

        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating all service fields", e);
    }
    }

    public ServiceDto getServiceDto(Service service) {
        try {
            FreelancerService.checkNull(service, "Service is null");
            return new ServiceDto(
                    service.getId(),
                    service.getTitre(),
                    service.getDescription(),
                    service.getPrix(),
                    service.getFreelancer(),
                    service.getCategorie()
            );
        } catch (Exception e) {
            throw new ServiceException("An error occurred while getting service dto", e);
        }

    }

    public ResponseEntity<String> modifierCategorie(Service service, Categorie categorie) {
        try {
           FreelancerService.checkNull(service, "Service is null");
            FreelancerService.checkNull(categorie, "Category is null");
            serviceRepository.updateServiceCategorie(categorie);
            return ResponseEntity.ok("Service category updated successfully");

        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service category", e);
        }
    }

    public ResponseEntity<String> modiferTitre(Service service, String titre) {
        try {
            FreelancerService.checkNull(service, "Service is null");
            FreelancerService.checkNull(titre, "Title is null");
            serviceRepository.updateServiceTitre(titre);
            return ResponseEntity.ok("Service title updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service title", e);
        }
    }

    public ServiceDto getServiceById(Long id) {

            FreelancerService.checkNull(id, "Id is null");
            return getServiceDto(serviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Service not found")));

    }


    public ServiceDto getServiceByCategorie(Categorie categorie) {
        try {
             FreelancerService.checkNull(categorie, "Category is null");
            return getServiceDto(serviceRepository.findByCategorie(categorie));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by category", e);
        }
    }

    public ServiceDto getServiceByTitre(String titre) {
        try {
            FreelancerService.checkNull(titre, "Title is null");
            return getServiceDto(serviceRepository.findByTitre(titre));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by title", e);
        }
    }

    public ServiceDto getServiceByPrix(Double prix) {
        try {
            FreelancerService.checkNull(prix, "Price is null");
            return getServiceDto(serviceRepository.findByPrix(prix));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by price", e);
        }
    }

    public ServiceDto getServiceByFreelancer(Freelancer freelancer) {
        try {
            FreelancerService.checkNull(freelancer, "Freelancer is null");
            return getServiceDto(serviceRepository.findByFreelancer(freelancer));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by freelancer", e);
        }
    }

    public ResponseEntity<String> modifierPrix(Service service, Double prix) {
        try {
             FreelancerService.checkNull(service, "Service is null");
            FreelancerService.checkNull(prix, "Price is null");
            serviceRepository.updateServicePrix(prix);
            return ResponseEntity.ok("Service price updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service price", e);
        }
    }

    public ResponseEntity<String> modifierDescription(Service service, String description) {
        try {
             FreelancerService.checkNull(service, "Service is null");
            FreelancerService.checkNull(description, "Description is null");

            serviceRepository.updateServiceDescription(description);
            return ResponseEntity.ok("Service description updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service description", e);
        }
    }

    public ResponseEntity<String> supprimerService(Service service) {
        try {
           FreelancerService.checkNull(service, "Service is null");

            serviceRepository.delete(service);
            return ResponseEntity.ok("Service deleted successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the service", e);
        }
    }

    public ResponseEntity<String> ajouterService(Service service, Categorie categorie) {
        try {
            FreelancerService.checkNull(service, "Service is null");
            FreelancerService.checkNull(categorie, "Category is null");
            service.setCategorie(categorie);
        serviceRepository.save(service);
            return ResponseEntity.ok("Service added successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while adding the service", e);
        }

    }


}
