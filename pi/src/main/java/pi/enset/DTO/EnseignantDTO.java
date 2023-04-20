package pi.enset.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.enset.entities.ElementDeModule;
import pi.enset.entities.NonDisponibilite;
import pi.enset.entities.Seance;

import java.util.ArrayList;
import java.util.Collection;

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
   /* private Collection<NonDisponibilite> nonDisponibilites;
    private Collection<Seance> seances;
    private Collection<ElementDeModule> elementDeModules = new ArrayList<>();*/
}
