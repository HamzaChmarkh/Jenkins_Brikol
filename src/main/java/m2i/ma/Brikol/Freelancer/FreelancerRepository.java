package m2i.ma.Brikol.Freelancer;

import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {


    Optional<Utilisateur> findByEmail(String email);
}
