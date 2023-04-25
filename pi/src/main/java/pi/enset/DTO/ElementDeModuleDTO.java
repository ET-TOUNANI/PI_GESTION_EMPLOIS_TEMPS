package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementDeModuleDTO {
    //Attributs
    private Long id;
    private int volumeHoraire;
    private String libelle;
    private ModuleDTO module;
    private EnseignantDTO enseignant;
}
