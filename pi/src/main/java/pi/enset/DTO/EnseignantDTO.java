package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantDTO {
    private Long id;
    private String civilite;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String login;
    private String password;
    private String specialite;
}
