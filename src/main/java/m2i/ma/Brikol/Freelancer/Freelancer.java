package m2i.ma.Brikol.Freelancer;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import m2i.ma.Brikol.Service.Service;
import m2i.ma.Brikol.User.Role;
import m2i.ma.Brikol.User.Utilisateur;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "freelancer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Freelancer extends Utilisateur {

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "public_email")
    private String publicEmail;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "zip")
    private String zip;

    @Column(name = "address")
    private String address;


    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Service.class)
    private List<Service> servicesProposes;


    public Freelancer(String nom, String email, Role role, String motDePasse, List<Service> servicesProposes) {
        super(nom, email, role, motDePasse);
        this.servicesProposes = servicesProposes;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && HibernateProxy.class.isAssignableFrom(o.getClass())) {
            return o.equals(this);
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            Freelancer freelancer = (Freelancer) o;
            return Objects.equals(getId(), freelancer.getId());
        }
    }


}
