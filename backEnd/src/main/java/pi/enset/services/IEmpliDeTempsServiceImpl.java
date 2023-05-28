package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.enset.GAlgo.GaAlgorithm;
import pi.enset.GAlgo.SchoolTimetable;
import pi.enset.entities.Classe;
import pi.enset.entities.ElementDeModule;
import pi.enset.settings.DataFromDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@AllArgsConstructor
public class IEmpliDeTempsServiceImpl implements IEmpliDeTempsService {
    private final DataFromDb dataFromDb;
    private final IElementDeModuleService elementDeModuleService;

    @Override
    public List<Map<Long, List<ElementDeModule>>> getAllEmplois() {
        List<Map<Long, List<ElementDeModule>>> emplois = new ArrayList<>();
        dataFromDb.loadDataFromDatabase();
        // Retrieve all classes
        List<Classe> classes = DataFromDb.classes;
        for (Classe classe : classes) {
            Map<Long, List<ElementDeModule>> emploi = new HashMap<>();
            emploi.put(classe.getId(), elementDeModuleService.getEmploisByClasse(classe.getId()));
            emplois.add(emploi);
        }
        return emplois;
    }

    @Override
    public List<ElementDeModule> getEmploisByClasse(Long id) {
        return elementDeModuleService.getEmploisByClasse(id);
    }

    @Override
    public List<Map<Long, List<ElementDeModule>>> generateEmplois() {
        List<Map<Long, List<ElementDeModule>>> emplois = new ArrayList<>();
        dataFromDb.loadDataFromDatabase();
        GaAlgorithm algorithm = new GaAlgorithm();
        SchoolTimetable schoolTimetable = algorithm.generateTimetable();

        for (int i = 0; i < schoolTimetable.getNumberOfClasses(); i++) {
            Map<Long, List<ElementDeModule>> emploi = new HashMap<>();
            emploi.put(schoolTimetable.getClasses().get(i).getId(), schoolTimetable.getTimetable(i));
            emplois.add(emploi);
        }

        for (ElementDeModule elementDeModule : schoolTimetable.getAllElements()) {
            elementDeModuleService.addElementDeModule(elementDeModule);
        }

        return emplois;
    }
}
