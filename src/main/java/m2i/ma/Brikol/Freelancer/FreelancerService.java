package m2i.ma.Brikol.Freelancer;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;


    @Autowired
    public FreelancerService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;

    }
    public static void checkNull(Object object, String message) {
        if (object == null) {
            throw new RuntimeException(message);
        } else if (message.isEmpty()) {
            throw new RuntimeException("empty message");
        }
    }

    public Freelancer createFreelancer(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            return freelancerRepository.save(freelancer);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating freelancer", e);
        }

    }
    public FreelancerDto getFreelancerDto(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
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
           checkNull(freelancers, "Freelancers is null");
            return freelancers.stream().map(this::getFreelancerDto).toList();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancers", e);
        }
    }
    public ResponseEntity<String> deleteFreelancer(Freelancer freelancer) {
        try {
        checkNull(freelancer, "Freelancer is null");
            freelancerRepository.delete(freelancer);
            return ResponseEntity.ok("Freelancer deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting freelancer", e);
        }
    }
     public ResponseEntity<String> deleteFreelancerById(Long id) {
         try {
               checkNull(id, "Id is null");
             freelancerRepository.deleteById(id);
             return ResponseEntity.ok("Freelancer deleted successfully");
         } catch (Exception e) {
             throw new RuntimeException("An error occurred while deleting freelancer", e);
         }

     }
    public Freelancer getFreelancerById(Long id) {
        try {
            checkNull(id, "Id is null");
            return freelancerRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by id", e);
        }
    }
    public Freelancer getFreelancerByUsername(String username) {
        try {
           checkNull(username, "Username is null");
            return freelancerRepository.findByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by username", e);
        }
    }




}