package alp.dev.rentcar.entity;


import alp.dev.rentcar.Roles.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "userRole")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    @Column(name = "password")
    private String password;

}
