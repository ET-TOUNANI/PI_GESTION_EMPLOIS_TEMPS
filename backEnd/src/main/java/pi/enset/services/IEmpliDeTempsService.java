package pi.enset.services;

import org.springframework.web.bind.annotation.PathVariable;
import pi.enset.entities.ElementDeModule;

import java.util.List;
import java.util.Map;

public interface IEmpliDeTempsService {
     List<Map<Long, List<ElementDeModule>>> getAllEmplois();
     List<ElementDeModule> getEmploisByClasse(Long id);
    List<Map<Long, List<ElementDeModule>>> generateEmplois();

    List<ElementDeModule> getEmploiByProf(Long id);
}
