package pi.enset.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.enums.JourDeLaSemaine;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NonDisponibilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection(targetClass = JourDeLaSemaine.class)
    @Enumerated(EnumType.STRING)
    private List<JourDeLaSemaine> jours;
    @ManyToOne
    private Enseignant enseignant;
}
