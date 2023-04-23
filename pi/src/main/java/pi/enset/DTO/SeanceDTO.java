package pi.enset.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import pi.enset.entities.enums.JourDeLaSemaine;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceDTO {

    private Long id;
    private JourDeLaSemaine jour;
    private Time heureDebut;
    private Time heureFin;
    private ClasseDTO classe;

    private SalleDTO salle;
    private ElementDeModuleDTO elementDeModule;
    private EnseignantDTO enseignant;
}
