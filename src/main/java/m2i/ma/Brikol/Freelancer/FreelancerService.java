package m2i.ma.Brikol.Freelancer;

import jakarta.persistence.EntityNotFoundException;
import m2i.ma.Brikol.Exceptions.ResponseDto;
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
                            freelancer.getServicesProposes(),
                            freelancer.getNickName(),
                            freelancer.getPublicEmail(),
                            freelancer.getDescription(),
                            freelancer.getPhoneNumber(),
                            freelancer.getRegion(),
                            freelancer.getCity(),
                            freelancer.getZip(),
                            freelancer.getAddress()


                    ),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer", e);
        }

    }


    public ResponseEntity<ResponseDto> deleteFreelancerById(Long id) {
        try {
            checkNull(id, "Id is null");
            freelancerRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseDto("Freelancer deleted successfully", 200));
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

    public ResponseEntity<String> modifyFreelancer(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            freelancerRepository.save(freelancer);
            return ResponseEntity.ok("Freelancer updated successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating freelancer", e);
        }
    }

}