package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service , Long> {

    Service findByCategorie(Categorie categorie);

    Service findByTitre(String titre);

    Service findByFreelancer(Freelancer freelancer);

    Service findByPrix(Double prix);

}

