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
public class ElementDeModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int volumeHoraire;
    private String libelle;
    @OneToMany(mappedBy = "elementDeModule")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Seance> seances;
    @ManyToOne
    private Module module;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Enseignant> enseignants = new ArrayList<>();

}
