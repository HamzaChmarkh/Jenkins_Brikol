package m2i.ma.Brikol.Freelancer;

import m2i.ma.Brikol.Categorie.Categorie;
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

    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }
    public void deleteFreelancer(Freelancer freelancer) {
        freelancerRepository.delete(freelancer);
    }

    public Freelancer getFreelancerById(Long id) {
        return freelancerRepository.findById(id).orElse(null);
    }
    public void updateFreelancer(Freelancer freelancer) {
        freelancerRepository.save(freelancer);
    }

}