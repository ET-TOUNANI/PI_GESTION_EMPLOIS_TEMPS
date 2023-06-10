package pi.enset.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pi.enset.entities.Enseignant;

import java.util.List;

public interface IEnseignantService {

    Enseignant addEnseignant(Enseignant enseignant);

    String deleteEnseignant(Long id);

    Enseignant getEnseignantById(Long id);

    Enseignant updateEnseignant(Long id, Enseignant enseignant);

    Page<Enseignant> getEnseignants(Pageable pageable);

    Page<Enseignant> searchEnseignants(String keyword, Pageable pageable);

    List<Enseignant> getAllEnseignants();

    List<Enseignant> findEnseignantByNom(String nom);
}
