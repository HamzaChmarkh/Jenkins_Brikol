package m2i.ma.Brikol.Freelancer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.Service.ServiceDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {
    private final FreelancerService freelancerService;

    @Autowired
    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteFreelancer(@NotNull @PathVariable Long id) {
        try {
            return freelancerService.deleteFreelancerById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDto("Error", 400));
        }

    }


    @GetMapping("/get/{id}")
    public ResponseEntity<FreelancerDto> getFreelancer(@PathVariable Long id) {
        try {
            return freelancerService.getFreelancerById(id);
        } catch (Exception e) {
            throw new ServiceException("An error occurred while fetvhing the freelancer", e);
        }

    }



    @PostMapping(value = "/Modify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> modifyFreelancer(@Valid @RequestBody Freelancer freelancer) {


        return freelancerService.modifyFreelancer(freelancer);
    }

}
