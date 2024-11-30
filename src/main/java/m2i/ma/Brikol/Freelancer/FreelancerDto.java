package m2i.ma.Brikol.Freelancer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2i.ma.Brikol.Service.Service;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreelancerDto {
     private String image;
    private Long id;
    private String name;
    private String username;
    private String email;
    private List<Service> servicesProposes;
    private String nickName;
    private String publicEmail;
    private String description;
    private String phoneNumber;
    private String region;
    private String city;
    private String zip;
    private String address;


    public FreelancerDto(String image, String username, String name, Long id, String email, List<Service> servicesProposes, String nickName, String publicEmail, String description, String phoneNumber, String region, String city, String zip, String address) {
        this.image = image;
        this.username = username;
        this.name = name;
        this.id = id;
        this.email = email;
        this.servicesProposes = servicesProposes;
        this.nickName = nickName;
        this.publicEmail = publicEmail;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.region = region;
        this.city = city;
        this.zip = zip;
        this.address = address;
    }
}
