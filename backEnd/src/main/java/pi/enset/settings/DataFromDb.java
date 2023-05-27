package pi.enset.settings;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pi.enset.entities.Module;
import pi.enset.entities.*;
import pi.enset.services.*;

import java.util.List;

@Component
@AllArgsConstructor
public class DataFromDb {
    public static List<Salle> rooms;
    public static List<Enseignant> professors;
    public static List<Classe> classes;
    public static List<Semestre> semestres;
    public static List<Module> modules;
    public static List<ElementDeModule> elementsDeModule;
    public static List<Filiere> filieres;
    public static List<Departement> departements;
    private ISalleService salleService;
    private IEnseignantService enseignantService;
    private IClasseService classeService;
    private ISemestreService semestreService;
    private IModuleService moduleService;
    private IElementDeModuleService elementDeModuleService;
    private IFiliereService filiereService;
    private IDepartementService departementService;

    public void loadDataFromDatabase() {
        // Retrieve the data from the services
        rooms = salleService.getSalles();
        professors = enseignantService.getAllEnseignants();
        classes = classeService.getClasses();
        semestres = semestreService.getSemestres();
        modules = moduleService.getModules();
        elementsDeModule = elementDeModuleService.getElementDeModule();
        filieres = filiereService.getFilieres();
        departements = departementService.getDepartements();
    }
}
