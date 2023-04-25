package pi.enset.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.enums.JourDeLaSemaine;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private JourDeLaSemaine jour;
    @Temporal(TemporalType.TIME)
    private LocalTime heureDebut;
    @Temporal(TemporalType.TIME)
    private LocalTime heureFin;
    @ManyToOne
    private Classe classe;
    @ManyToOne
    private Salle salle;
    @ManyToOne
    private ElementDeModule elementDeModule;
    @ManyToOne
    private Enseignant enseignant;


}
