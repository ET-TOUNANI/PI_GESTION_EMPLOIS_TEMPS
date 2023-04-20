package pi.enset.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeSalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle; // mini-amphi , amphi theatre ...
    private int capacite;
    private String equipement;// ?

    @OneToMany(mappedBy = "typeSalle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Salle> salles;
}
