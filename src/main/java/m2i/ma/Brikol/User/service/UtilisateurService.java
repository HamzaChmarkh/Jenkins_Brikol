package m2i.ma.Brikol.User.service;

import m2i.ma.Brikol.User.dto.FirstLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UtilisateurService {
    UserDetailsService userDetailsService();
    ResponseEntity<String> newFreelancer(FirstLoginRequest firstLoginRequest);
}
