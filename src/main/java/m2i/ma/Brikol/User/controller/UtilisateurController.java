package m2i.ma.Brikol.User.controller;

import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.User.dto.FirstLoginRequest;
import m2i.ma.Brikol.User.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @PostMapping("/first_login")
    public ResponseEntity<String> firstLogin(@RequestBody FirstLoginRequest firstLoginRequest){
        return utilisateurService.newFreelancer(firstLoginRequest);
    }
}
