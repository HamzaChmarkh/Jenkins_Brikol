package m2i.ma.Brikol.Freelancer;

import jakarta.validation.Valid;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ResponseDto> deleteFreelancer(@PathVariable Long id) {
        return freelancerService.deleteFreelancerById(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FreelancerDto> getFreelancer(@PathVariable Long id) {
        return freelancerService.getFreelancerById(id);
    }


    @GetMapping("/get/{username}")
    public ResponseEntity<FreelancerDto> getFreelancer(@PathVariable String username) {
        return freelancerService.getFreelancerByUsername(username);
    }

    @PostMapping("/Modify")
    public ResponseEntity<ResponseDto> modifyFreelancer(@Valid @RequestBody FreelancerDto freelancerDto) {
        Freelancer freelancer = freelancerService.toFreelancer(freelancerDto);

        return freelancerService.modifyFreelancer(freelancer);
    }

}
