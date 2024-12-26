package m2i.ma.Brikol.Admin;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import m2i.ma.Brikol.Categorie.CategorieRepository;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Freelancer.FreelancerDto;
import m2i.ma.Brikol.Freelancer.FreelancerRepository;
import m2i.ma.Brikol.Service.ServiceRepository;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.UtilisateurRepository;
import m2i.ma.Brikol.User.dto.UtilisateurResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {


    private final AdminRepository adminRepository;


    private final UtilisateurRepository userRepository;


    private final ServiceRepository serviceRepository;


    private final CategorieRepository categorieRepository;

    private final UtilisateurRepository utilisateurRepository;


    private FreelancerRepository freelancerRepository;

    @Autowired
    public  AdminService(AdminRepository adminRepository, UtilisateurRepository userRepository, ServiceRepository serviceRepository, CategorieRepository categorieRepository, UtilisateurRepository utilisateurRepository, FreelancerRepository freelancerRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.categorieRepository = categorieRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.freelancerRepository = freelancerRepository;
    }

    public List<UtilisateurResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UtilisateurResponse response = new UtilisateurResponse();
                    response.setId(user.getId().toString());
                    response.setEmail(user.getEmail());
                    response.setName(user.getUsername());
                    response.setImage(user.getImage());
                    response.setNewUser(user.isNewUser());
                    return response;
                })
                .toList();
    }







    // Delete a user
    public ResponseDto supprimerUtilisateur(Long utilisateurId) {
        if (!utilisateurRepository.existsById(utilisateurId)) {
            return new ResponseDto("Utilisateur introuvable.", HttpStatus.NOT_FOUND.value());
        }
        utilisateurRepository.deleteById(utilisateurId);
        return new ResponseDto("Utilisateur supprimé avec succès.", HttpStatus.OK.value());
    }

        // Consulter tous les utilisateurs
        public ResponseEntity<?> consulterTousLesUtilisateurs() {
            return ResponseEntity.ok(utilisateurRepository.findAll());
        }

    // calculate number of categories
    public long calculerNombreDeCategories() {
        return categorieRepository.count();
    }

    public ResponseDto suspendUser(Long userId) {
        Utilisateur user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setSuspended(true); // Assuming the 'suspended' field exists in Utilisateur
        userRepository.save(user);
        return new ResponseDto("User suspended successfully", HttpStatus.OK.value());
    }


    public StatistiquesDto consulterStatistiques() {
        long totalUsers = utilisateurRepository.count();
        long totalFreelancers = utilisateurRepository.countByRole(Role.Freelancer);
        long totalClients = utilisateurRepository.countByRole(Role.Client);
        long totalServices = serviceRepository.count();
        long totalCategories = categorieRepository.count();

        return new StatistiquesDto(totalUsers, totalFreelancers, totalClients, totalServices,totalCategories);
    }

    // get freelancers details like services and personal info
    public FreelancerDto getFreelancerDetails(Long freelancerId) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .filter(user -> user.getRole() == Role.Freelancer)
                .orElseThrow(() -> new RuntimeException("Freelancer not found"));

        return  freelancer.toFreelancerDto();
    }

}
