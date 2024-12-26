package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service , Long> {

    List<Service> findByIdcategorie(Categorie idcategorie);

    List<Service> findByTitre(String titre);

    List<Service> findByIdfreelancer(Long idfreelancer);

    List<Service> findByPrix(Double prix);

}

