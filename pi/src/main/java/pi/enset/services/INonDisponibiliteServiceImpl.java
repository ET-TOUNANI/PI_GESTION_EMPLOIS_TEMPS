package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.NonDisponibilite;
import pi.enset.repository.NonDisponibiliteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class INonDisponibiliteServiceImpl implements INonDisponibiliteService {
    private NonDisponibiliteRepository nonDisponibiliteRepository;

    @Override
    public List<NonDisponibilite> getNonDisponibilites() {
        return nonDisponibiliteRepository.findAll();
    }

    @Override
    public NonDisponibilite addNonDisponibilite(NonDisponibilite nonDisponibilite) {

        return nonDisponibiliteRepository.save(nonDisponibilite);
    }

    @Override
    public String deleteNonDisponibilite(Long id) {
        try {
            getNonDisponibiliteById(id);
            nonDisponibiliteRepository.deleteById(id);
            return "L'opération est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public NonDisponibilite getNonDisponibiliteById(Long id) {
        return nonDisponibiliteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cette non Disponibilité n'existe pas."));
    }

    @Override
    public NonDisponibilite updateNonDisponibilite(Long id, NonDisponibilite nonDisponibilite) {
        nonDisponibilite.setId(id);
        return nonDisponibiliteRepository.save(nonDisponibilite);
    }
}
