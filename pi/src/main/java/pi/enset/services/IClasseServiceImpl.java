package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.Classe;
import pi.enset.repository.ClasseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IClasseServiceImpl implements IClasseService {
    private ClasseRepository classeRepository;

    @Override
    public List<Classe> getClasses() {
        return classeRepository.findAll();
    }

    @Override
    public Classe addClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    @Override
    public String deleteClasse(Long id) {
        try {
            getClasseById(id);
            classeRepository.deleteById(id);
            return "La suppression de classe est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Classe getClasseById(Long id) {
        return classeRepository.findById(id).orElseThrow(() -> new RuntimeException("La classe n'existe pas!"));
    }

    @Override
    public Classe updateClasse(Long id, Classe classe) {
        classe.setId(id);
        return classeRepository.save(classe);
    }

}
