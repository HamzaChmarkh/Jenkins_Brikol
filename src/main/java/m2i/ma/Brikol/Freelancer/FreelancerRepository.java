package m2i.ma.Brikol.Freelancer;

import java.util.List;


import m2i.ma.Brikol.Categorie.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    void ajoutrService(Service service, Categorie categorie);
    void modifierService(Service service, Categorie categorie);
    void supprimerService(m2i.ma.Brikol.Service.Service service);
}
