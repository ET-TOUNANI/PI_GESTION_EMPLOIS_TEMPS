package pi.enset.DTO;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pi.enset.entities.enums.JourDeLaSemaine;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceDTO {

    private Long id;
    private JourDeLaSemaine jour;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime heureDebut;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime heureFin;
    private ClasseDTO classe;
    private SalleDTO salle;
    private ElementDeModuleDTO elementDeModule;
    private EnseignantDTO enseignant;
}
