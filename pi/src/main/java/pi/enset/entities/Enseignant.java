package pi.enset.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DiscriminatorValue("ENSEIGNANT")
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String civilite;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    @Column(unique = true)
    private String login;
    private String password;
    private String specialite;
    @OneToMany(mappedBy = "enseignant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<NonDisponibilite> nonDisponibilites;

    @OneToMany(mappedBy = "enseignant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Seance> seances;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "enseignants")
    private Collection<ElementDeModule> elementDeModules = new ArrayList<>();

}
