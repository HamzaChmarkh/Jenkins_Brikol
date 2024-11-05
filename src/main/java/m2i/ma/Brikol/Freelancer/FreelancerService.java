package m2i.ma.Brikol.Freelancer;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public ResponseEntity<FreelancerDto> getFreelancerDto(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            return new ResponseEntity<>(
                    new FreelancerDto(
                            freelancer.getImage(),
                            freelancer.getId(),
                            freelancer.getNom(),
                            freelancer.getUsername(),
                            freelancer.getEmail(),
                            freelancer.getServicesProposes()
                    ),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer", e);
        }

    }

    //    public List<FreelancerDto> getFreelancerDto(List<Freelancer> freelancers) {
//        try {
//           checkNull(freelancers, "Freelancers is null");
//            return freelancers.stream().map(this::getFreelancerDto).toList();
//        } catch (Exception e) {
//            throw new RuntimeException("An error occurred while getting freelancers", e);
//        }
//    }
    public ResponseEntity<String> deleteFreelancerByUsername(String username) {
        try {
            checkNull(username, "Username is null");
            freelancerRepository.deleteByUsername(username);
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

    public ResponseEntity<FreelancerDto> getFreelancerById(Long id) {
        try {
            checkNull(id, "Id is null");
            return getFreelancerDto(freelancerRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by id", e);
        }
    }

    public ResponseEntity<FreelancerDto> getFreelancerByUsername(String username) {
        try {
            checkNull(username, "Username is null");
            return getFreelancerDto(freelancerRepository.findByUsername(username));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by username", e);
        }
    }

    public ResponseEntity<FreelancerDto> getFreelancerByName(String nom) {
        try {
            checkNull(nom, "Nom is null");
            return getFreelancerDto(freelancerRepository.findByNom(nom));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by name", e);
        }


    }

    public ResponseEntity<String> deleteFreelancerByName(String nom) {
        try {
            checkNull(nom, "Nom is null");
            freelancerRepository.deleteByNom(nom);
            return ResponseEntity.ok("Freelancer deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting freelancer by name", e);
        }
    }

    public ResponseEntity<String> modifyNom(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            checkNull(freelancer.getNom(), "Nom is null");
            freelancerRepository.updateNom(freelancer.getNom());
            return ResponseEntity.ok("Freelancer name updated successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating freelancer name", e);
        }
    }

    public ResponseEntity<String> modifyFreelancer(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            freelancerRepository.save(freelancer);
            return ResponseEntity.ok("Freelancer updated successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating freelancer", e);
        }
    }

    public ResponseEntity<String> modifyServicesProposes(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            checkNull(freelancer.getServicesProposes(), "Services proposes is null");
            freelancerRepository.updateServicesProposes(freelancer.getServicesProposes());
            return ResponseEntity.ok("Services proposes updated successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating services proposes", e);
        }
    }

    public ResponseEntity<String> modifyImage(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            checkNull(freelancer.getImage(), "Image is null");
            freelancerRepository.updateImage(freelancer.getImage());
            return ResponseEntity.ok("Image updated successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating image", e);
        }
    }

    public ResponseEntity<String> modifyUsername(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            checkNull(freelancer.getUsername(), "Username is null");
            freelancerRepository.updateUsername(freelancer.getUsername());
            return ResponseEntity.ok("Username updated successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating username", e);
        }
    }

    public ResponseEntity<String> modifyEmail(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            checkNull(freelancer.getEmail(), "Email is null");
            freelancerRepository.updateEmail(freelancer.getEmail());
            return ResponseEntity.ok("Email updated successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating email", e);
        }
    }
}