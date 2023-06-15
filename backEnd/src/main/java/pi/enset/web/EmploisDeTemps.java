package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.GAlgo.GaAlgorithm;
import pi.enset.GAlgo.SchoolTimetable;
import pi.enset.entities.Classe;
import pi.enset.entities.ElementDeModule;
import pi.enset.services.IElementDeModuleService;
import pi.enset.services.IEmpliDeTempsService;
import pi.enset.settings.DataFromDb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/emploisDeTemps")
@AllArgsConstructor
public class EmploisDeTemps {
    private final IEmpliDeTempsService empliDeTempsService;

    @GetMapping
    public List<Map<Long, List<ElementDeModule>>> getAllEmplois() {
        return empliDeTempsService.getAllEmplois();
    }

    @GetMapping("/{id}")
    public List<ElementDeModule> getEmploisByClasse(@PathVariable Long id) {
        return empliDeTempsService.getEmploisByClasse(id);
    }

    @GetMapping("/generate")
    public List<Map<Long, List<ElementDeModule>>> generateEmplois() {
       return empliDeTempsService.generateEmplois();
    }
    //getEmploiByProf
    @GetMapping("/prof/{id}")
    public  List<ElementDeModule>getEmploiByProf(@PathVariable Long id) {

        return empliDeTempsService.getEmploiByProf(id);
    }

}
