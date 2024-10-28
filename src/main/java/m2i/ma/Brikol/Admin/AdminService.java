package m2i.ma.Brikol.Admin;
import m2i.ma.Brikol.Service.ServiceRepository;
import m2i.ma.Brikol.User.Utilisateur;
import m2i.ma.Brikol.User.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
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

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public void suspendUser(Long userId) {
        Utilisateur user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setSuspended(true); // Assuming a 'suspended' field exists in Utilisateur
        userRepository.save(user);
    }

    public void validateService(Long serviceId) {
        Service service = serviceRepository.findById(serviceId).orElseThrow(() -> new RuntimeException("Service not found"));
        service.setValidated(true); // Assuming a 'validated' field exists in Service
        serviceRepository.save(service);
    }

    public void resetUserPassword(Long userId) {
        Utilisateur user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword("new_default_password"); // Set a default or temporary password
        userRepository.save(user);
    }
}
