package m2i.ma.Brikol.Freelancer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
    // Additional custom queries for Freelancer can go here, if needed

    @Override
    <S extends Freelancer> S saveAndFlush(S entity);
}
