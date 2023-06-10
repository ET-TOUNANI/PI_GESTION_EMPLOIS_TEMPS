package pi.enset.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ROLE",length = 10)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String civilite;
    private String nom;
    @Column(name = "ROLE",insertable = false,updatable = false)
    private String Role;
    private String prenom;
    private String tel;
    private String cne;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String login;
    private String password;
    // default value = false
    @Column(columnDefinition = "boolean default false")
    private boolean isAuthentificated;
}
