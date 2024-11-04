package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service , Long> {

    void updateServiceCategorie(Categorie categorie);

    void updateServiceTitre(String titre);

    void updateServicePrix(Double prix);

    void updateServiceDescription(String description);

    

    Service findByCategorie(Categorie categorie);

    Service findByTitre(String titre);

    Service findByFreelancer(Freelancer freelancer);

    Service findByPrix(Double prix);

    Service findByDescription(String description);
}

