package m2i.ma.Brikol.Freelancer;

import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {

    Optional<Utilisateur> findByEmail(String email);


    Freelancer findByUsername(String username);

    void deleteByUsername(String username);

    Freelancer findByNom(String nom);


    void updateNom(String nom);

    void updateServicesProposes(List<Service> servicesProposes);

    void deleteByNom(String nom);

    void updateImage(String image);

    void updateUsername(String username);

    void updateEmail(String email);
}
