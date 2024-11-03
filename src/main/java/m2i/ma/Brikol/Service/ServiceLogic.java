package m2i.ma.Brikol.Service;

import jakarta.persistence.EntityNotFoundException;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Freelancer.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceLogic {


    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceLogic(FreelancerRepository freelancerRepository, ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void modfierTous(Service service, Categorie categorie, String titre, Double prix, String description) {
        serviceRepository.updateServiceByCategorie(categorie);
        serviceRepository.updateServiceByTitre(titre);
        serviceRepository.updateServiceByPrix(prix);
        serviceRepository.updateServiceByDescription(description);
        serviceRepository.updateServiceByFreelancer(service.getFreelancer());
    }

    public void modifierFreelancer(Service service, Freelancer freelancer) {
        serviceRepository.updateServiceByFreelancer(freelancer);
    }

    public void modifierCategory(Service service, Categorie categorie) {
        serviceRepository.updateServiceByCategorie(categorie);
    }

    public void modiferTitre(String titre) {
        serviceRepository.updateServiceByTitre(titre);
    }

    public Service getServiceById(Long id) {
        try {
            return serviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sevice not found"));
        } catch (Exception e){
            e.printStackTrace();
            throw new ServcieException("An error occured while fetching the service");
        }
    }

    public Service getServiceByCategorie(Categorie categorie) {
        return serviceRepository.findByCategorie(categorie);
    }

    public Service getServiceByTitre(String titre) {
        return serviceRepository.findByTitre(titre);
    }

    public Service getServiceByPrix(Double prix) {
        return serviceRepository.findByPrix(prix);
    }

    public Service getServiceByFreelancer(Freelancer freelancer) {
        return serviceRepository.findByFreelancer(freelancer);
    }

    public void modifierPrix(Double prix) {
        serviceRepository.updateServiceByPrix(prix);
    }

    public void modifierDescription(String description) {
        serviceRepository.updateServiceByDescription(description);

    }

    public void supprimerService(Service service) {
        serviceRepository.delete(service);
    }

    public void ajouterService(Service service, Categorie categorie) {
        serviceRepository.save(service);
    }


}
