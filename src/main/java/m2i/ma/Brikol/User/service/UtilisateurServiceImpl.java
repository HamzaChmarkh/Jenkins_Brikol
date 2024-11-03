package m2i.ma.Brikol.User.service;

import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.User.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return utilisateurRepository
                        .findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            }
        };
    }
}
