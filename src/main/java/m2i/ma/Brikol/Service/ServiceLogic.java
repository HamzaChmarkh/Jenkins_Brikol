package m2i.ma.Brikol.Service;

import jakarta.persistence.EntityNotFoundException;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
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
            if (service == null ) {
                throw new ServiceException("Service is null");
            }else if (categorie == null) {
                throw new ServiceException("Categorie is null");
            }
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
            if (service == null) {
                throw new ServiceException("Service is null");
            }
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
            if(service == null ) {
                throw new ServiceException("Service is null");
            }else if (categorie == null) {
                throw new ServiceException("Categorie is null");
            }
            serviceRepository.updateServiceCategorie(categorie);
            return ResponseEntity.ok("Service category updated successfully");

        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service category", e);
        }
    }

    public ResponseEntity<String> modiferTitre(Service service, String titre) {
        try {
            if(service == null) {
                throw new ServiceException("Service is null");
            }else if (titre == null) {
                throw new ServiceException("Title is null");
            }
            serviceRepository.updateServiceTitre(titre);
            return ResponseEntity.ok("Service title updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service title", e);
        }
    }

    public ServiceDto getServiceById(Long id) {
        try {
            if(id == null) {
                throw new ServiceException("Id is null");
            }
            return getServiceDto(serviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Service not found")));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by id", e);
        }
    }


    public ServiceDto getServiceByCategorie(Categorie categorie) {
        try {
            if(categorie == null) {
                throw new ServiceException("Categorie is null");
            }
            return getServiceDto(serviceRepository.findByCategorie(categorie));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by category", e);
        }
    }

    public ServiceDto getServiceByTitre(String titre) {
        try {
            if(titre == null) {
                throw new ServiceException("Title is null");
            }
            return getServiceDto(serviceRepository.findByTitre(titre));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by title", e);
        }
    }

    public ServiceDto getServiceByPrix(Double prix) {
        try {
            if(prix == null) {
                throw new ServiceException("Price is null");
            }
            return getServiceDto(serviceRepository.findByPrix(prix));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by price", e);
        }
    }

    public ServiceDto getServiceByFreelancer(Freelancer freelancer) {
        try {
            if(freelancer == null) {
                throw new ServiceException("Freelancer is null");
            }
            return getServiceDto(serviceRepository.findByFreelancer(freelancer));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by freelancer", e);
        }
    }

    public ResponseEntity<String> modifierPrix(Service service, Double prix) {
        try {
            if (service == null) {
                throw new ServiceException("Service is null");
            } else if (prix == null) {
                throw new ServiceException("Price is null");
            }
            serviceRepository.updateServicePrix(prix);
            return ResponseEntity.ok("Service price updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service price", e);
        }
    }

    public ResponseEntity<String> modifierDescription(Service service, String description) {
        try {
            if(service == null) {
                throw new ServiceException("Service is null");
            }else if (description == null) {
                throw new ServiceException("Description is null");
            }
            serviceRepository.updateServiceDescription(description);
            return ResponseEntity.ok("Service description updated successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service description", e);
        }
    }

    public ResponseEntity<String> supprimerService(Service service) {
        try {
            if(service == null) {
                throw new ServiceException("Service is null");
            }
            serviceRepository.delete(service);
            return ResponseEntity.ok("Service deleted successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the service", e);
        }
    }

    public ResponseEntity<String> ajouterService(Service service, Categorie categorie) {
        try {
            if(service == null) {
                throw new ServiceException("Service is null");
            }else if (categorie == null) {
                throw new ServiceException("Categorie is null");
            }
            service.setCategorie(categorie);
        serviceRepository.save(service);
            return ResponseEntity.ok("Service added successfully");
        } catch (Exception e) {
            throw new ServiceException("An error occurred while adding the service", e);
        }

    }


}
