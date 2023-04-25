package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.Seance;
import pi.enset.repository.SeanceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ISeanceServiceImpl implements ISeanceService {
    private SeanceRepository seanceRepository;

    @Override
    public List<Seance> getSeances() {
        return seanceRepository.findAll();
    }

    @Override
    public Seance addSeance(Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public String deleteSeance(Long id) {
        try {
            getSeanceById(id);
            seanceRepository.deleteById(id);
            return "L'opération est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Seance getSeanceById(Long id) {
        return seanceRepository.findById(id).orElseThrow(() -> new RuntimeException("La seance n'existe pas!"));

    }

    @Override
    public Seance updateSeance(Long id, Seance seance) {
        seance.setId(id);
        return seanceRepository.save(seance);
    }
}
