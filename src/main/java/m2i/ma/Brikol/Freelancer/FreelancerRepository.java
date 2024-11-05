package m2i.ma.Brikol.Freelancer;

import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.Categorie.Categorie;
import m2i.ma.Brikol.Service.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {

    Optional<Utilisateur> findByEmail(String email);

    Arrays findByServicesProposes(String service);

    Freelancer findByUsername(String username);
}
