package m2i.ma.Brikol.Admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.Service.ServiceRepository;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import m2i.ma.Brikol.Service.ServiceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
    public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }
    //
//    public List<Service> getAllServices() {
//        return serviceRepository.findAll();
//    }


        private final UtilisateurRepository utilisateurRepository;

        // Ajouter un nouvel administrateur
        public ResponseEntity<String> ajouterAdmin(Admin admin) {
            if (utilisateurRepository.findByEmail(admin.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Email déjà utilisé.");
            }
            adminRepository.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Administrateur ajouté avec succès.");
        }

        // Supprimer un utilisateur (Client ou Freelancer) par un admin
        public ResponseEntity<String> supprimerUtilisateur(Long utilisateurId) {
            if (!utilisateurRepository.existsById(utilisateurId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Utilisateur introuvable.");
            }
            utilisateurRepository.deleteById(utilisateurId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Utilisateur supprimé avec succès.");
        }

        // Consulter tous les utilisateurs
        public ResponseEntity<?> consulterTousLesUtilisateurs() {
            return ResponseEntity.ok(utilisateurRepository.findAll());
        }

        public void suspendUser(Long userId) {
        Utilisateur user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setSuspended(true); //  'suspended' field exists in Utilisateur
        userRepository.save(user);
    }

    public void validateService(Long serviceId) {
        m2i.ma.Brikol.Service.Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));
        service.setValidated(true); // Assuming a 'validated' field exists in Service
        serviceRepository.save(service);
    }
    public StatistiquesDto consulterStatistiques() {
        long totalUsers = utilisateurRepository.count();
        long totalFreelancers = utilisateurRepository.countByRole(Role.Freelancer);
        long totalClients = utilisateurRepository.countByRole(Role.Client);
        long totalServices = serviceRepository.count();

        return new StatistiquesDto(totalUsers, totalFreelancers, totalClients, totalServices);
    }
    }


//

//
//    public void resetUserPassword(Long userId) {
//        Utilisateur user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        user.setPassword("new_default_password"); // Set a default or temporary password
//        userRepository.save(user);
//    }

