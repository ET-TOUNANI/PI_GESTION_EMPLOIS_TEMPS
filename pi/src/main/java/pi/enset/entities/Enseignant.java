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
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@DiscriminatorValue("ENSEIGNANT")
public class Enseignant extends User{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enseignant_id;
    private  String specialite;
    @OneToMany(mappedBy = "enseignant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Seance> seances;
    @OneToMany(mappedBy = "enseignant")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<NonDisponibilite>nonDisponibilites;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "enseignants")
    private Collection<ElementDeModule>elementDeModules=new ArrayList<>();
}
