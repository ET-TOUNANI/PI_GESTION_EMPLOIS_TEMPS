package pi.enset.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.enums.JourDeLaSemaine;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NonDisponibilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private JourDeLaSemaine jour;
    @ManyToOne
    private Enseignant enseignant;
}
