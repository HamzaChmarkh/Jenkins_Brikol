package m2i.ma.Brikol.Admin;

import m2i.ma.Brikol.Categorie.CategorieDto;
import m2i.ma.Brikol.Categorie.CategorieService;
import m2i.ma.Brikol.Client.Client;
import m2i.ma.Brikol.Client.ClientService;
import m2i.ma.Brikol.Exceptions.ResponseDto;
import m2i.ma.Brikol.Freelancer.FreelancerDto;
import m2i.ma.Brikol.Service.ServiceDto;
import m2i.ma.Brikol.Service.ServiceLogic;
import m2i.ma.Brikol.Service.ServiceRepository;
import m2i.ma.Brikol.User.dto.UtilisateurResponse;
import org.springframework.beans.factory.annotation.Autowired;
import m2i.ma.Brikol.Service.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private final AdminService adminService;

    @Autowired
    private final ServiceRepository serviceRepository;

    @Autowired
    private final ServiceLogic serviceLogic;

    @Autowired
    private final CategorieService categorieService;

    @Autowired
    private final ClientService clientService;
    @Autowired
    public AdminController(AdminService adminService, ServiceRepository serviceRepository, ServiceLogic serviceLogic, CategorieService categorieService, ClientService clientService) {
        this.adminService = adminService;
        this.serviceRepository = serviceRepository;
        this.serviceLogic = serviceLogic;
        this.categorieService = categorieService;
        this.clientService = clientService;
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

    //Api to get all categories
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategorieDto>> getAllCategories() {
        return categorieService.getAllCategories();
    }

    //Api to get all clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAllClients();
    }



    // get freelancers details like services and personal info when clicked
    @GetMapping("/freelancer/{id}")
    public ResponseEntity<FreelancerDto> getFreelancerWithDetails(@PathVariable Long id){

        try{
            FreelancerDto freelancerdto = adminService.getFreelancerDetails(id);

            return ResponseEntity.ok(freelancerdto);
        }
        catch(Exception e){
            ResponseDto rpd = new ResponseDto("Error in fetching freelancer details " + e.getMessage(),500);
            return ResponseEntity.status(500).body(null);

        }


    }




    @GetMapping("/statistiques")
    public ResponseEntity<StatistiquesDto> consulterStatistiques() {
        StatistiquesDto stats = adminService.consulterStatistiques();
        return ResponseEntity.ok(stats);
    }

    // Endpoint pour récupérer tous les services
    @GetMapping("/getAllServices")
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        try {
            // Get all services from the repository
            List<Service> services = serviceRepository.findAll();

            // Manually map List<Service> to List<ServiceDto>
            List<ServiceDto> serviceDtos = services.stream()
                    .map(service -> new ServiceDto(service.getId(), service.getTitre(), service.getDescription(),service.getPrix(),service.getPathImage(),service.getIdFreelancer(),service.getCategorie()))
                    .collect(Collectors.toList());

            // Return the List of ServiceDto objects wrapped in ResponseEntity
            return ResponseEntity.ok(serviceDtos);
        } catch (Exception e) {
            // Handle errors and send a failure response
            ResponseDto errorResponse = new ResponseDto("Failed to fetch services: " + e.getMessage(), 500);
            return ResponseEntity.status(500).body(null);
        }
    }




    // Suspend user endpoint
    @PostMapping("/suspend-user/{userId}")
    public ResponseEntity<ResponseDto> suspendUser(@PathVariable Long userId) {
        try {
            ResponseDto response = adminService.suspendUser(userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ResponseDto errorResponse = new ResponseDto("Failed to suspend user: " + e.getMessage(), 500);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }



    // Delete service because it's against our policy
    @PostMapping("/deleteService")
    public ResponseEntity<ResponseDto> deleteService(@RequestBody Service service) {
        try {
            // Call the service logic to delete the service and return a ResponseEntity

            // Return the ResponseEntity as is
            return serviceLogic.supprimerService(service);
        } catch (Exception e) {
            // Handle errors and send a failure response
            ResponseDto errorResponse = new ResponseDto("Failed to delete service: " + e.getMessage(), 500);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

}


