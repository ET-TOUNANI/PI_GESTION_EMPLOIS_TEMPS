package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.Enseignant;
import pi.enset.entities.enums.JourDeLaSemaine;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonDisponibiliteDTO {
    private Long id;
    private JourDeLaSemaine jour;
    private Enseignant enseignant;
}
