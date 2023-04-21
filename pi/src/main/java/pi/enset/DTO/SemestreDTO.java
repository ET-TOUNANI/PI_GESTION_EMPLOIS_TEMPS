package pi.enset.DTO;
<<<<<<< HEAD
=======

>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5
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
