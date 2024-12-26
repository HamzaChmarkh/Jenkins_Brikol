package m2i.ma.Brikol.Freelancer;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2i.ma.Brikol.User.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FreelancerDto  {
    @NotNull(message = "ID cannot be null")
    private Long id;



    private Role role = Role.Freelancer;

    @Email(message = "Public Email must be valid")
    private String publicEmail;

    @NotBlank(message = "Nickname cannot be blank")
    @Size(min = 2, max = 50, message = "Nickname must be between 2 and 50 characters")
    private String nickName;



    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^\\+?[0-9. ()-]{7,25}$",
            message = "Phone number must be valid"
    )
    private String phoneNumber;

    @NotBlank(message = "Region cannot be blank")
    private String region;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @Pattern(
            regexp = "^\\d{5}$",
            message = "ZIP code must be a valid 5-digit number"
    )
    private String zip;



    @NotBlank(message = "Address cannot be blank")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;
    @NotBlank(message = "Image URL cannot be blank")
    private String image;


    public Freelancer toFreelancer(FreelancerDto freelancerDto) {
        Freelancer freelancer = new Freelancer();
        freelancer.setId(freelancerDto.getId());
        freelancer.setNickName(freelancerDto.getNickName());
        freelancer.setPublicEmail(freelancerDto.getPublicEmail());
        freelancer.setDescription(freelancerDto.getDescription());
        freelancer.setPhoneNumber(freelancerDto.getPhoneNumber());
        freelancer.setRegion(freelancerDto.getRegion());
        freelancer.setCity(freelancerDto.getCity());
        freelancer.setZip(freelancerDto.getZip());
        freelancer.setAddress(freelancerDto.getAddress());
        freelancer.setImage(freelancerDto.getImage());
        freelancer.setRole(freelancerDto.getRole());
        return freelancer;
    }
}

