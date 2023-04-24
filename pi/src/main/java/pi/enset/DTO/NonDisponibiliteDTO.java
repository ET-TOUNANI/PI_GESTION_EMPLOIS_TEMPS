package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.enums.JourDeLaSemaine;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonDisponibiliteDTO {
    private Long id;
    private List<JourDeLaSemaine> jours;
    // private Enseignant enseignant;
}
