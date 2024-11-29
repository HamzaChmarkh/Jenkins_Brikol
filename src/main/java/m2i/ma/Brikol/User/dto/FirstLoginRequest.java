package m2i.ma.Brikol.User.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FirstLoginRequest {
    private Long userId;
    private String nickName;
    private String publicEmail;
    private String description;
    private String phoneNumber;
    private String region;
    private String city;
    private String zip;
    private String address;
    private List<Long> categories;
}
