package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.entities.Module;
import pi.enset.repository.ModuleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IModuleServiceImpl implements IModuleService {
    private ModuleRepository moduleRepository;

    @Override
    public List<Module> getModules() {
        return moduleRepository.findAll();
    }

    @Override
    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public String deleteModule(Long id) {
        try {
            getModuleById(id);
            moduleRepository.deleteById(id);
            return "L'opération est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Module getModuleById(Long id) {

        return moduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Ce module n'existe pas."));
    }

    @Override
    public Module updateModule(Long id, Module module) {
        module.setId(id);
        return moduleRepository.save(module);
    }
}
