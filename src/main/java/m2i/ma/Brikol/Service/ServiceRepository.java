package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service , Long> {

    List<Service> findByCategorie(Categorie categorie);

    List<Service> findByTitre(String titre);

    List<Service> findByFreelancer(Freelancer freelancer);

    List<Service> findByPrix(Double prix);

}

