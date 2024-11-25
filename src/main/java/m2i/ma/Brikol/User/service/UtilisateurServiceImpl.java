package m2i.ma.Brikol.User.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.UtilisateurRepository;
import m2i.ma.Brikol.User.dto.FirstLoginRequest;
import m2i.ma.Brikol.User.mapper.UtilisateurMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public ResponseEntity<String> newFreelancer(FirstLoginRequest firstLoginRequest) {
        // Retrieve the user or throw an exception if not found
        Utilisateur user = utilisateurRepository.findById(firstLoginRequest.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Check if the user has already completed the first login
        if (!user.isNewUser()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User has already completed the first login process.");
        }

        if(!user.getRole().equals(Role.Freelancer)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User is not a freelancer.");
        }
//        List<m2i.ma.Brikol.Service.Service> services = serviceRepository.findAllById(firstLoginRequest.getServices());
        Freelancer freelancer = UtilisateurMapper.toFreelancer(firstLoginRequest, user);
        utilisateurRepository.save(freelancer); // Persist Freelancer
        return ResponseEntity.status(HttpStatus.OK)
                .body("Freelancer profile successfully created.");
    }


}
