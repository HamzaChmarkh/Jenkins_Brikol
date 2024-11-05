package m2i.ma.Brikol.Freelancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public FreelancerDto getFreelancerDto(Freelancer freelancer) {
        try {
            return new FreelancerDto(
                    freelancer.getImage(),
                    freelancer.getId(),
                    freelancer.getNom(),
                    freelancer.getUsername(),
                    freelancer.getEmail(),
                    freelancer.getServicesProposes()
            );
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer", e);
        }

    }

    public List<FreelancerDto> getFreelancerDto(List<Freelancer> freelancers) {
        try {
            return freelancers.stream().map(this::getFreelancerDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancers", e);
        }
    }
    public ResponseEntity<String> deleteFreelancer(Freelancer freelancer) {
        try {
            freelancerRepository.delete(freelancer);
            return ResponseEntity.ok("Freelancer deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting freelancer", e);
        }
    }
     public ResponseEntity<String> deleteFreelancerById(Long id) {
         try {
               if (id == null) {
                throw new RuntimeException("Id is null");
            }
             freelancerRepository.deleteById(id);
             return ResponseEntity.ok("Freelancer deleted successfully");
         } catch (Exception e) {
             throw new RuntimeException("An error occurred while deleting freelancer", e);
         }

     }
    public Freelancer getFreelancerById(Long id) {
        try {
            if (id == null) {
                throw new RuntimeException("Id is null");
            }
            return freelancerRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by id", e);
        }
    }
    public Freelancer getFreelancerByUsername(String username) {
        try {
            if (username == null) {
                throw new RuntimeException("Username is null");
            }
            return freelancerRepository.findByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by username", e);
        }
    }




}