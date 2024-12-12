package m2i.ma.Brikol.User.resetpassword;

import m2i.ma.Brikol.User.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResetPasswordRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    boolean existsByToken(String token);
    Optional<PasswordResetToken> findByUser(Utilisateur user);
    Optional<PasswordResetToken> findByUid(String uid);

}
