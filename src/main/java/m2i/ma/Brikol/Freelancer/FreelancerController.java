package m2i.ma.Brikol.Freelancer;

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

    @DeleteMapping("/delete/{username}")
    public void deleteFreelancer(@PathVariable String username) {
        freelancerService.deleteFreelancerByUsername(username);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFreelancer(@PathVariable Long id) {
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

    @PostMapping("/Modify/nom")
    public ResponseEntity<String> modifyNom(@RequestBody Freelancer freelancer) {
        return freelancerService.modifyNom(freelancer);
    }

    @PostMapping("/Modify/email")
    public ResponseEntity<String> modifyEmail(@RequestBody Freelancer freelancer) {
        return freelancerService.modifyEmail(freelancer);
    }

    @PostMapping("/Modify/username")
    public ResponseEntity<String> modifyUsername(@RequestBody Freelancer freelancer) {
        return freelancerService.modifyUsername(freelancer);
    }

    @PostMapping("/Modify/image")
    public ResponseEntity<String> modifyImage(@RequestBody Freelancer freelancer) {
        return freelancerService.modifyImage(freelancer);
    }

    @PostMapping("/Modify/servicesProposes")
    public ResponseEntity<String> modifyServicesProposes(@RequestBody Freelancer freelancer) {
        return freelancerService.modifyServicesProposes(freelancer);
    }

    @PostMapping("/Modify")
    public ResponseEntity<String> modifyFreelancer(@RequestBody Freelancer freelancer) {
        return freelancerService.modifyFreelancer(freelancer);
    }
}
