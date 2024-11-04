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
public class FreelancerDto extends UtilisateurResponse {
    private List<Service> servicesProposes;

    public FreelancerDto(String id, String email, String name, String image, List<Service> servicesProposes) {
        super(id, email, name, image);
        this.servicesProposes = servicesProposes;
    }
}
