package m2i.ma.Brikol.Client;
import lombok.*;
import m2i.ma.Brikol.Service.Service;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String image;
    private Long id;
    private String name;
    private String email;
    private String motDePasse;
}
