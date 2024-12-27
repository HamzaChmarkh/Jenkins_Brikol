package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service , Long> {

    List<Service> findByCategorie(Long id);

    List<Service> findByTitre(String titre);

    List<Service> findByIdFreelancer(Long idFreelancer);

    List<Service> findByPrix(Double prix);

}

