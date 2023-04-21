package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDTO {
    //Attributs
    private Long id;
    private String libelle;
    private int nbrEleves;
    /*
    #Associations
    private Filiere filiere;
    private Collection<Seance> seances;
     */
}
