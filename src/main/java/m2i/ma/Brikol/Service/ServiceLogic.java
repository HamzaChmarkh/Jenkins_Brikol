package m2i.ma.Brikol.Service;

import jakarta.persistence.EntityNotFoundException;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceLogic {


    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceLogic( ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void modfierTous(Service service, Categorie categorie) {
        try {
        serviceRepository.updateServiceByCategorie(categorie);
        serviceRepository.updateServiceByTitre(service.getTitre());
        serviceRepository.updateServiceByPrix(service.getPrix());
        serviceRepository.updateServiceByDescription(service.getDescription());
        serviceRepository.updateServiceByFreelancer(service.getFreelancer());

        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating all service fields", e);
    }
    }

    public void modifierFreelancer(Service service, Freelancer freelancer) {
        try {
            serviceRepository.updateServiceByFreelancer(freelancer);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service freelancer", e);
    }
    }

    public void modifierCategory(Service service, Categorie categorie) {
        try {
            serviceRepository.updateServiceByCategorie(categorie);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service category", e);
    }
    }

    public void modiferTitre(String titre) {
        try {
            serviceRepository.updateServiceByTitre(titre);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service title", e);
        }
    }
    public Service getServiceById(Long id) {
        try {
            return serviceRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Service with id: " + id + " not found"));
        } catch (EntityNotFoundException e) {
            throw e; // Relancer l'exception pour qu'elle soit capturée par le ControllerAdvice
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service", e);
        }
    }


    public Service getServiceByCategorie(Categorie categorie) {
        try {
        return serviceRepository.findByCategorie(categorie);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by category", e);
        }
    }

    public Service getServiceByTitre(String titre) {
        try {
        return serviceRepository.findByTitre(titre);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by title", e);
        }
    }

    public Service getServiceByPrix(Double prix) {
        try {
        return serviceRepository.findByPrix(prix);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by price", e);
        }
    }

    public Service getServiceByFreelancer(Freelancer freelancer) {
        try {
            return serviceRepository.findByFreelancer(freelancer);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by freelancer", e);
        }
    }

    public void modifierPrix(Double prix) {
        try {
            serviceRepository.updateServiceByPrix(prix);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service price", e);
        }
    }

    public void modifierDescription(String description) {
        try {
            serviceRepository.updateServiceByDescription(description);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service description", e);
        }
    }

    public void supprimerService(Service service) {
        try {
            serviceRepository.delete(service);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the service", e);
        }
    }

    public void ajouterService(Service service, Categorie categorie) {
        try {
        serviceRepository.save(service);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while adding the service", e);
        }
    }


}
