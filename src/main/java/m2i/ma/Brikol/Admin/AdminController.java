//package m2i.ma.Brikol.Admin;
//import m2i.ma.Brikol.User.Utilisateur;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @GetMapping("/users")
//    public ResponseEntity<List<Utilisateur>> getAllUsers() {
//        return ResponseEntity.ok(adminService.getAllUsers());
//    }
//
//    @GetMapping("/services")
//    public ResponseEntity<List<Service>> getAllServices() {
//        return ResponseEntity.ok(adminService.getAllServices());
//    }
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
