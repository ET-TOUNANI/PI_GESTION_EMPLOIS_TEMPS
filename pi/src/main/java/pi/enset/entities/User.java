package pi.enset.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "ROLE",length = 10)
public abstract class User {
    //@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String civilite;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    //@Column(unique = true)
    private String login;
    private String password;

}
