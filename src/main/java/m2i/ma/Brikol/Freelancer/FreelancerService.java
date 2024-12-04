package m2i.ma.Brikol.Freelancer;

import jakarta.persistence.EntityNotFoundException;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Service.ServiceLogic;
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

    public static void checkFreelancer(Freelancer freelancer) {
        if (freelancer == null) {
            throw new RuntimeException("Freelancer is null");
        } else if (freelancer.getNom().isEmpty()) {
            throw new RuntimeException("Freelancer name is empty");
        } else if (freelancer.getUsername().isEmpty()) {
            throw new RuntimeException("Freelancer username is empty");
        } else if (freelancer.getEmail().isEmpty()) {
            throw new RuntimeException("Freelancer email is empty");
        } else if (freelancer.getServicesProposes().isEmpty()) {
            throw new RuntimeException("Freelancer services is empty");
        } else if (freelancer.getNickName().isEmpty()) {
            throw new RuntimeException("Freelancer nickname is empty");
        } else if (freelancer.getPublicEmail().isEmpty()) {
            throw new RuntimeException("Freelancer public email is empty");
        } else if (freelancer.getDescription().isEmpty()) {
            throw new RuntimeException("Freelancer description is empty");
        } else if (freelancer.getPhoneNumber().isEmpty()) {
            throw new RuntimeException("Freelancer phone number is empty");
        } else if (freelancer.getRegion().isEmpty()) {
            throw new RuntimeException("Freelancer region is empty");
        } else if (freelancer.getCity().isEmpty()) {
            throw new RuntimeException("Freelancer city is empty");
        } else if (freelancer.getZip().isEmpty()) {
            throw new RuntimeException("Freelancer zip is empty");
        } else if (freelancer.getAddress().isEmpty()) {
            throw new RuntimeException("Freelancer address is empty");
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
    public ResponseEntity<ResponseDto> addServiceToFreelancer(Long id, m2i.ma.Brikol.Service.Service service) {
        try {
            checkNull(id, "Id is null");
            checkNull(service, "Service is null");
            ServiceLogic.checkService(service);
            if (!freelancerRepository.existsById(id)) {
                throw new RuntimeException("Freelancer not found");
            }
            Freelancer freelancer = freelancerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            freelancer.getServicesProposes().add(service);
            freelancerRepository.save(freelancer);
            return ResponseEntity.ok(new ResponseDto("Service added successfully", 200));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding service to freelancer", e);
        }
    }

    public ResponseEntity<ResponseDto> deleteFreelancerById(Long id) {
        try {
            checkNull(id, "Id is null");
            if (!freelancerRepository.existsById(id)) {
                throw new RuntimeException("Freelancer not found");
            }
            freelancerRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseDto("Freelancer deleted successfully", 200));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting freelancer", e);
        }

    }

    public ResponseEntity<FreelancerDto> getFreelancerById(Long id) {
        try {
            checkNull(id, "Id is null");
            if (!freelancerRepository.existsById(id)) {
                throw new RuntimeException("Freelancer not found");
            }
            return getFreelancerDto(freelancerRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by id", e);
        }
    }

    public ResponseEntity<FreelancerDto> getFreelancerByUsername(String username) {
        try {
            checkNull(username, "Username is null");
            if (!freelancerRepository.existsByUsername(username)) {
                throw new RuntimeException("Freelancer not found");
            }
            return getFreelancerDto(freelancerRepository.findByUsername(username));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while getting freelancer by username", e);
        }
    }

    public ResponseEntity<ResponseDto> modifyFreelancer(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            checkFreelancer(freelancer);
            if (!freelancerRepository.existsById(freelancer.getId())) {
                throw new RuntimeException("Freelancer not found");
            }

            freelancerRepository.save(freelancer);
            return ResponseEntity.ok(new ResponseDto("Freelancer updated successfully", 200));

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating freelancer", e);
        }
    }

    public Freelancer toFreelancer(FreelancerDto freelancerDto) {
        return new Freelancer(
                freelancerDto.getImage(),
                freelancerDto.getId(),
                freelancerDto.getName(),
                freelancerDto.getUsername(),
                freelancerDto.getEmail(),
                freelancerDto.getServicesProposes(),
                freelancerDto.getNickName(),
                freelancerDto.getPublicEmail(),
                freelancerDto.getDescription(),
                freelancerDto.getPhoneNumber(),
                freelancerDto.getRegion(),
                freelancerDto.getCity(),
                freelancerDto.getZip(),
                freelancerDto.getAddress()
        );
    }

}