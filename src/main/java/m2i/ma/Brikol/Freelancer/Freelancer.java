package m2i.ma.Brikol.Freelancer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.Utilisateur;

import java.util.List;

@Entity
@Table(name = "freelancer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Freelancer extends Utilisateur {


    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    private List<Service> servicesProposes;

}
