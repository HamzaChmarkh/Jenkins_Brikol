package m2i.ma.Brikol.Admin;

import m2i.ma.Brikol.Admin.Admin;
import m2i.ma.Brikol.Admin.AdminService;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Service.ServiceDto;
import m2i.ma.Brikol.Service.ServiceLogic;
import m2i.ma.Brikol.Service.ServiceRepository;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.dto.UtilisateurResponse;
import org.springframework.beans.factory.annotation.Autowired;
import m2i.ma.Brikol.Service.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceLogic serviceLogic;


    // Endpoint pour ajouter un administrateur
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> ajouterAdmin(@RequestBody Admin admin) {
        try {
            // Call the service method, which returns ResponseDto
            ResponseDto response = adminService.ajouterAdmin(admin);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle unexpected exceptions
            ResponseDto errorResponse = new ResponseDto("Failed to add admin: " + e.getMessage(), 500);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }




    // Endpoint pour supprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> supprimerUtilisateur(@PathVariable Long id) {
        try {
            adminService.supprimerUtilisateur(id);
            ResponseDto response = new ResponseDto("User deleted successfully", 200);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto response = new ResponseDto("Failed to delete user: " + e.getMessage(), 500);
            return ResponseEntity.status(500).body(response);
        }
    }



    // Endpoint pour consulter tous les utilisateurs
    @GetMapping("/allusers")
    public ResponseEntity<?> consulterTousLesUtilisateurs() {
        return adminService.consulterTousLesUtilisateurs();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UtilisateurResponse> utilisateurs = adminService.getAllUsers();
            return ResponseEntity.ok(utilisateurs);
        } catch (Exception e) {
            ResponseDto errorResponse = new ResponseDto("Failed to fetch users: " + e.getMessage(), 500);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }




    @GetMapping("/statistiques")
    public ResponseEntity<StatistiquesDto> consulterStatistiques() {
        StatistiquesDto stats = adminService.consulterStatistiques();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/getAllServices")
    public ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(serviceRepository.findAll());
    }

    // search a service by freelancer so as to validate it or delete it
    @GetMapping("/getByFreelancer")
    public ResponseEntity<ServiceDto>  getServiceByFreelancer(@RequestBody Freelancer freelancer) {
        return   serviceLogic.getServiceByFreelancer(freelancer);
    }

    //delete service because it's against our policy and manners
    @PostMapping("/deleteService")
    public ResponseEntity<ResponseDto> deleteService(@RequestBody Service service) {
        return serviceLogic.supprimerService(service);
    }
}

//

//
//
//    @PostMapping("/suspend-user/{userId}")
//    public ResponseEntity<?> suspendUser(@PathVariable Long userId) {
//        adminService.suspendUser(userId);
//        return ResponseEntity.ok("User suspended successfully.");
//    }
//
//    @PostMapping("/validate-service/{serviceId}")
//    public ResponseEntity<?> validateService(@PathVariable Long serviceId) {
//        adminService.validateService(serviceId);
//        return ResponseEntity.ok("Service validated successfully.");
//    }
//
//    @PostMapping("/reset-password/{userId}")
//    public ResponseEntity<?> resetUserPassword(@PathVariable Long userId) {
//        adminService.resetUserPassword(userId);
//        return ResponseEntity.ok("User password reset successfully.");
//    }
//}
