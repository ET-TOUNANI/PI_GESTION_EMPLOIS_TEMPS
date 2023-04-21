
package pi.enset.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.enums.NumeroSemester;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemestreDTO {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private NumeroSemester num;
    private String anneeUniv;
}
