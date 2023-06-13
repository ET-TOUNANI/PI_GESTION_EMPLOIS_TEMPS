package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.Semestre;
import pi.enset.entities.enums.NumeroSemester;
import pi.enset.repository.SemestreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ISemestreServiceImpl implements ISemestreService {
    private SemestreRepository semestreRepository;

    @Override
    public List<Semestre> getSemestres() {
        return semestreRepository.findAll();
    }

    @Override
    public Semestre addSemestre(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    @Override
    public String deleteSemestre(Long id) {
        try {
            getSemestreById(id);
            semestreRepository.deleteById(id);
            return "L'opération est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Semestre getSemestreById(Long id) {
        return semestreRepository.findById(id).orElseThrow(() -> new RuntimeException("Le semestre n'existe pas!"));
    }

    @Override
    public Semestre updateSemestre(Long id, Semestre semestre) {
        semestre.setId(id);
        return semestreRepository.save(semestre);
    }

    @Override
    public List<Semestre> findSemestreByNum(NumeroSemester numeroSemester) {
        return semestreRepository.findSemestreByNum(numeroSemester);
    }

}
