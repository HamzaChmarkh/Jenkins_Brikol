package m2i.ma.Brikol.Admin;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
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

    @Autowired
    private final CategorieRepository categorieRepository;


    @Autowired
    private FreelancerRepository freelancerRepository;

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




    private final UtilisateurRepository utilisateurRepository;


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
//
//
//    public ResponseDto validateService(Long serviceId) {
//        m2i.ma.Brikol.Service.Service service = serviceRepository.findById(serviceId)
//                .orElseThrow(() -> new RuntimeException("Service not found"));
//        service.setValidated(true); // Assuming a 'validated' field exists in Service
//        serviceRepository.save(service);
//        return new ResponseDto("Service validated successfully", HttpStatus.OK.value());
//    }

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
                .filter(user -> user instanceof Freelancer)
                .orElseThrow(() -> new RuntimeException("Freelancer not found"));

        FreelancerDto dto = new FreelancerDto();
        dto.setId(freelancer.getId());
        dto.setName(freelancer.getNom());
        dto.setUsername(freelancer.getUsername());
        dto.setEmail(freelancer.getEmail());
        dto.setNickName(freelancer.getNickName());
        dto.setServicesProposes(freelancer.getServicesProposes());
        dto.setRegion(freelancer.getRegion());
        dto.setCity(freelancer.getCity());
        return dto;
    }

    }
