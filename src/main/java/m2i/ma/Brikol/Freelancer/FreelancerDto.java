package m2i.ma.Brikol.Freelancer;

import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Image URL cannot be blank")
    private String image;

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9_.-]*$",
            message = "Username can only contain letters, numbers, underscores, hyphens, and dots"
    )
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "ServicesProposes cannot be null")
    private List<Service> servicesProposes;

    @NotBlank(message = "Nickname cannot be blank")
    @Size(min = 2, max = 50, message = "Nickname must be between 2 and 50 characters")
    private String nickName;

    @Email(message = "Public Email must be valid")
    private String publicEmail;

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

    public Freelancer toFreelancer() {
        return new Freelancer(
                image,id,
                name,
                username,
                email,
                servicesProposes,
                nickName,
                publicEmail,
                description,
                phoneNumber,
                region,
                city,
                zip,
                address
        );
    }
}
