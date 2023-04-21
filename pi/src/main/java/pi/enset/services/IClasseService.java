package pi.enset.services;

import pi.enset.entities.Classe;
import pi.enset.entities.Departement;
import pi.enset.entities.Enseignant;

import java.util.List;

public interface IClasseService {
    List<Classe> getClasses();

    Classe addClasse(Classe classe);
    String deleteClasse(Long id);

    Classe getClasseById(Long id);

    Classe updateClasse(Long id, Classe classe);

}
