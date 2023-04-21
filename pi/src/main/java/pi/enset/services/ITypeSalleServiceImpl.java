package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.TypeSalle;
import pi.enset.repository.TypeSalleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ITypeSalleServiceImpl implements ITypeSalleService {
    private TypeSalleRepository typeSalleRepository;

    @Override
    public List<TypeSalle> getTypeSalles() {
        return typeSalleRepository.findAll();
    }

    @Override
    public TypeSalle addTypeSalle(TypeSalle typeSalle) {
        return typeSalleRepository.save(typeSalle);
    }

    @Override
    public String deleteTypeSalle(Long id) {
        try {
            getTypeSalleById(id);
            typeSalleRepository.deleteById(id);
            return "L'opération est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public TypeSalle getTypeSalleById(Long id) {
        return typeSalleRepository.findById(id).orElseThrow(() -> new RuntimeException("Le type de salle n'existe pas!"));
    }

    @Override
    public TypeSalle updateTypeSalle(Long id, TypeSalle typeSalle) {
        typeSalle.setId(id);
        return typeSalleRepository.save(typeSalle);
    }

}