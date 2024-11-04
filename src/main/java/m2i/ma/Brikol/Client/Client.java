package m2i.ma.Brikol.Client;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;

import java.util.List;

@Entity
@Table(name = "client")
@Getter
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Client extends Utilisateur {


    public Client(String nom, String email, Role role, String motDePasse) {
        super(nom, email, role, motDePasse);
    }
    public void chercherService(String critere) {
        // Logic for searching services based on the criterion
    }

    public void contacterFreelancer(Freelancer freelancer) {
        // Logic to contact a freelancer
    }




}
