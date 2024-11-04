package m2i.ma.Brikol.Client;


import m2i.ma.Brikol.User.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Utilisateur> findByEmail(String email);
    // Add custom queries if necessary
}
