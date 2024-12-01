package m2i.ma.Brikol.Admin;

import jakarta.persistence.*;
import lombok.*;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;

@Entity
@DiscriminatorValue("Admin")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends Utilisateur {

//    public void gérerUtilisateurs() {
//        // Logic for managing users (e.g., viewing, editing, deleting users)
//    }
//
//    public void gérerService() {
//        // Logic for managing services (e.g., viewing, approving, deleting services)
//    }
//
//    public void voirStatistiques() {
//        // Logic for viewing statistics (e.g., total users, total services)
//    }
//
//    public void suspendreUtilisateur(Utilisateur user) {
//        // Logic to suspend a user
//    }
//
//    public void validerService(Service service) {
//        // Logic to validate a service
//    }

    private String adminCode; // Code spécifique pour l'authentification de l'admin

    public Admin(String nom, String email,Role role, String motDePasse, String adminCode) {
        super(nom, email, Role.Admin, motDePasse);
        this.adminCode = adminCode;
    }


}
