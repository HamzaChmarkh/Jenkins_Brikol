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


    private String adminCode; // Code spécifique pour l'authentification de l'admin

    //
    public Admin(String nom, String email,Role role, String motDePasse, String adminCode) {
        super(nom, email, Role.Admin, motDePasse);
        this.adminCode = adminCode;
    }


}
