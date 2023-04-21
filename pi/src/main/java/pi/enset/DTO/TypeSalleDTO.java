package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeSalleDTO {
    private Long id;
    private String libelle;
    private int capacite;
    private String equipement;
}
