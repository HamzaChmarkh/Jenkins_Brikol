package m2i.ma.Brikol.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Freelancer.FreelancerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Service
@Transactional
public class ServiceLogic {


    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceLogic( ServiceRepository serviceRepository) {

        this.serviceRepository = serviceRepository;
    }

    public ResponseEntity<ResponseDto> modfierTous(Service service, Categorie categorie) {
        try {
            FreelancerService.checkNull(service, "Service is null");
            FreelancerService.checkNull(categorie, "Category is null");
            service.setCategorie(categorie);
            serviceRepository.save(service);
            return ResponseEntity.ok(new ResponseDto("Service updated successfully", 200));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while updating the service", e);
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





    public ResponseEntity<ServiceDto>  getServiceById(Long id) {
        try {
            FreelancerService.checkNull(id, "Id is null");
            return ResponseEntity.ok(getServiceDto(serviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Service not found"))));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by id", e);
        }

    }


    public ResponseEntity<ServiceDto>  getServiceByCategorie(Categorie categorie) {
        try {
            FreelancerService.checkNull(categorie, "Category is null");
            return ResponseEntity.ok(getServiceDto(serviceRepository.findByCategorie(categorie)));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by category", e);
        }

    }


    public ResponseEntity<ServiceDto> getServiceByTitre(String titre) {
        try {
            FreelancerService.checkNull(titre, "Titre is null");
            return ResponseEntity.ok(getServiceDto(serviceRepository.findByTitre(titre)));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by title", e);
        }
    }



    public ResponseEntity<ServiceDto>  getServiceByPrix(Double prix) {
        try {
            FreelancerService.checkNull(prix, "Prix is null");
            return ResponseEntity.ok(getServiceDto(serviceRepository.findByPrix(prix)));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by price", e);

        }
    }

    public ResponseEntity<ServiceDto> getServiceByFreelancer(Freelancer freelancer) {
        try {
            FreelancerService.checkNull(freelancer, "Freelancer is null");
            return ResponseEntity.ok(getServiceDto(serviceRepository.findByFreelancer(freelancer)));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetching the service by freelancer", e);
        }

    }


    public ResponseEntity<ResponseDto> supprimerService(Service service) {
        try {
            FreelancerService.checkNull(service, "Service is null");
            serviceRepository.delete(service);
            return ResponseEntity.ok(new ResponseDto("Service deleted successfully", 200));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while deleting the service", e);
        }

    }

    public ResponseEntity<ResponseDto> ajouterService(Service service, Categorie categorie) {
        try {
            FreelancerService.checkNull(service, "Service is null");
            FreelancerService.checkNull(categorie, "Category is null");
            service.setCategorie(categorie);
            serviceRepository.save(service);
            return ResponseEntity.ok(new ResponseDto("Service created successfully", 200));
        } catch (Exception e) {
            throw new ServiceException("An error occurred while creating the service", e);
        }


    }


}
