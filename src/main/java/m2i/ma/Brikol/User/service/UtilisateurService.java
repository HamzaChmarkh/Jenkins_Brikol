package m2i.ma.Brikol.User.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UtilisateurService {
    public UserDetailsService userDetailsService();
}
