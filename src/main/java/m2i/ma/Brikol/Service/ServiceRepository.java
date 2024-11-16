package m2i.ma.Brikol.Service;

import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Freelancer.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service , Long> {
@Modifying
@Query("UPDATE Service s SET s.categorie = :categorie WHERE s.id = :id")
    void updateServiceCategorie(Categorie categorie);
    Service findByCategorie(Categorie categorie);

    Service findByTitre(String titre);

    Service findByFreelancer(Freelancer freelancer);

    Service findByPrix(Double prix);

}

