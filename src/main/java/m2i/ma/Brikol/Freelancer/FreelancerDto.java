package m2i.ma.Brikol.Freelancer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.dto.UtilisateurResponse;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreelancerDto {
     private String image;
    private Long id;
    private String nom;
    private String username;
    private String email;
    private List<Service> servicesProposes;

}
