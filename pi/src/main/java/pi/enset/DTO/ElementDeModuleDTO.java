package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.Enseignant;
import pi.enset.entities.Module;
import pi.enset.entities.Seance;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementDeModuleDTO {
    //Attributs
    private Long id;
    private int volumeHoraire;
    private String libelle;
    /*
    Associations
    private Collection<Seance> seances;
    private Module module;
    private Collection<Enseignant> enseignants = new ArrayList<>();
     */

}
