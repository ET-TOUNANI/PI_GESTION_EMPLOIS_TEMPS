package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pi.enset.entities.Enseignant;
import pi.enset.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IEnseignantServiceImpl implements IEnseignantService {
    private UserRepository userRepository;

    @Override
    public Enseignant addEnseignant(Enseignant enseignant) {
        return (Enseignant) userRepository.save(enseignant);
    }

    @Override
    public String deleteEnseignant(Long id) {
        try {
            getEnseignantById(id);
            userRepository.deleteById(id);
            return "L'operation est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Enseignant getEnseignantById(Long id) {
        return (Enseignant) userRepository.findById(id).orElseThrow(() -> new RuntimeException("L'enseignant avec l'id " + id + " n'existe pas!"));
    }

    @Override
    public Enseignant updateEnseignant(Long id, Enseignant enseignant) {
        enseignant.setId(id);
        return (Enseignant) userRepository.save(enseignant);

    }
    @Override
    public Page<Enseignant> getEnseignants(Pageable pageable) {
        return  userRepository.findUsersByRole("PROF",pageable);
    }


    @Override
    public Page<Enseignant> searchEnseignants(String keyword, Pageable pageable) {
        return  userRepository.searchWithPagination(keyword, pageable);
    }
    @Override
    public List<Enseignant> getAllEnseignants() {
        return  userRepository.findAllByRole("PROF");
    }

    @Override
    public List<Enseignant> findEnseignantByNom(String nom) {
        return  userRepository.findEnseignantByNom(nom);
    }

}
