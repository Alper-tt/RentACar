package alp.dev.rentcar.entity;


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

    @Column(name = "first Name")
    private String firstName;

    @Column(name = "last Name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phone;

}
