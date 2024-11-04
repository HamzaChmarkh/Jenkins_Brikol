package m2i.ma.Brikol.Freelancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FreelancerService {
    @Autowired
    private FreelancerRepository freelancerRepository;

    public Freelancer createFreelancer(Freelancer freelancer) {
        return freelancerRepository.save(freelancer);
    }


}