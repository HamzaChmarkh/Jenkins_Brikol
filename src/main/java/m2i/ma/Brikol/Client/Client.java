package m2i.ma.Brikol.Client;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import m2i.ma.Brikol.Freelancer.Freelancer;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.Utilisateur;

import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client extends Utilisateur {

    @Id
    private Long id;

    public void chercherService(String critere) {
        // Logic for searching services based on the criterion
    }

    public void contacterFreelancer(Freelancer freelancer) {
        // Logic to contact a freelancer
    }




}
