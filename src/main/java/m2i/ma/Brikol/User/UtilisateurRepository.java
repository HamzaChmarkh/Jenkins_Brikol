package m2i.ma.Brikol.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);

    @Modifying
    @Query("UPDATE Utilisateur u SET u.emailVerifier = TRUE WHERE u.email = ?1")
    int enableUtilisateur(String email);
}
