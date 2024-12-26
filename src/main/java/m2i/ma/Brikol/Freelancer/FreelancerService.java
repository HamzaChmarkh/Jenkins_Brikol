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
        checkNull(freelancer, "Freelancer is null");
        checkNull(freelancer.getNickName(), "Nickname is null");
        checkNull(freelancer.getPublicEmail(), "Public email is null");
        checkNull(freelancer.getDescription(), "Description is null");
        checkNull(freelancer.getPhoneNumber(), "Phone number is null");
        checkNull(freelancer.getRegion(), "Region is null");
        checkNull(freelancer.getCity(), "City is null");
        checkNull(freelancer.getZip(), "ZIP code is null");
        checkNull(freelancer.getAddress(), "Address is null");
        checkNull(freelancer.getImage(), "Image URL is null");
    }



    public ResponseEntity<FreelancerDto> getFreelancerDto(Freelancer freelancer) {
        try {
            checkNull(freelancer, "Freelancer is null");
            return new ResponseEntity<>(
                    freelancer.toFreelancerDto(),
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



}