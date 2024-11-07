package m2i.ma.Brikol.Visitor;

import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Service.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/homepage")
public class HomePageController {

    private final HomePageService homePageService;

    @Autowired
    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    @GetMapping("/announcements")
    public ResponseEntity<List<Service>> getAnnouncements() {
        return homePageService.getAllAnnouncements();
    }

    @PostMapping("/contact")
    public ResponseEntity<ResponseDto> sendContactMessage(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message) {
        return homePageService.sendContactMessage(name, email, message);
    }
}
