package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.ElementDeModule;
import pi.enset.repository.ElementModuleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IElementDeModuleServiceImpl implements IElementDeModuleService {
    private ElementModuleRepository elementModuleRepository;

    @Override
    public List<ElementDeModule> getElementDeModule() {
        return elementModuleRepository.findAll();
    }

    @Override
    public ElementDeModule addElementDeModule(ElementDeModule elementDeModule) {
        return elementModuleRepository.save(elementDeModule);
    }

    @Override
    public String deleteElementDeModule(Long id) {
        try {
            getElementDeModuleById(id);
            elementModuleRepository.deleteById(id);
            return "La suppresion est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public ElementDeModule getElementDeModuleById(Long id) {
        return elementModuleRepository.findById(id).orElseThrow(() -> new RuntimeException("L'element de module du numéro " + id + " n'existe pas!"));
    }

    @Override
    public ElementDeModule updateElementDeModule(Long id, ElementDeModule elementDeModule) {
        elementDeModule.setId(id);
        return elementModuleRepository.save(elementDeModule);
    }

    @Override
    public List<ElementDeModule> getEmploisByClasse(Long classeId) {
        return elementModuleRepository.getEmploisByClasse(classeId);
    }
}
