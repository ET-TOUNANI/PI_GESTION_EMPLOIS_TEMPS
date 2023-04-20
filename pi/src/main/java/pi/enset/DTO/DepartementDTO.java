package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.Filiere;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartementDTO {
    private Long id;
    private String libelle;
    //private Collection<Filiere> filieres;
}
