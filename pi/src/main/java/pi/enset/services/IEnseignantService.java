package pi.enset.services;

import pi.enset.entities.Enseignant;

import java.util.List;

public interface IEnseignantService {
    List<Enseignant> getEnseignants();

    Enseignant addEnseignant(Enseignant enseignant);

    String deleteEnseignant(Long id);

    Enseignant getEnseignantById(Long id);

    Enseignant updateEnseignant(Long id, Enseignant enseignant);
}
