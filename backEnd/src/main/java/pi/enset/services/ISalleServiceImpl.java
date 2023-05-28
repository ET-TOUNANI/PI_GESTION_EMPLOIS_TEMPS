package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.Salle;
import pi.enset.repository.SalleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ISalleServiceImpl implements ISalleService {
    private SalleRepository salleRepository;

    @Override
    public List<Salle> getSalles() {
        return salleRepository.findAll();
    }

    @Override
    public Salle addSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    @Override
    public String deleteSalle(Long id) {
        try {
            getSalleById(id);
            salleRepository.deleteById(id);
            return "Votre supression est effectuée avec succès";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Salle getSalleById(Long id) {
        return salleRepository.findById(id).orElseThrow(() -> new RuntimeException("La salle n'existe pas!"));
    }

    @Override
    public Salle updateSalle(Long id, Salle salle) {
        salle.setId(id);
        return salleRepository.save(salle);
    }
}
