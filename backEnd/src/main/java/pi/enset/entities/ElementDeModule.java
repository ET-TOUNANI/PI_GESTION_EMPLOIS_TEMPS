package pi.enset.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.enums.Periode;

import java.time.DayOfWeek;

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
    @Enumerated(EnumType.STRING)
    private DayOfWeek jour;
    @Enumerated(EnumType.STRING)
    private Periode periode;

    @ManyToOne
    private Salle salle;
    @ManyToOne
    private Module module;
    @ManyToOne
    private Enseignant enseignant;
}
