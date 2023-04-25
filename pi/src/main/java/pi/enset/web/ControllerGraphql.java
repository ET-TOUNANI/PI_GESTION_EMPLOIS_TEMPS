package pi.enset.web;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pi.enset.DTO.*;
import pi.enset.DTO.mappers.GeneralMapper;
import pi.enset.entities.Module;
import pi.enset.entities.*;
import pi.enset.services.*;

import java.util.List;

@Controller
public class ControllerGraphql {
    // don't forget to add your service here

    private final ISemestreService semestreService;
    private final ITypeSalleService typeSalleService;
    private final IDepartementService departementService;
    private final IEnseignantService enseignantService;
    //Classe and ElementModule
    private final IClasseService classeService;
    private final IElementDeModuleService elementDeModuleService;

    private final ISeanceService seanceService;

    private final IFiliereService filiereService;
    private final ISalleService salleService;

    private final IModuleService moduleService;
    private final INonDisponibiliteService nonDisponibiliteService;

    //Repositories
    // don't forget to customize your mapper here
    private final GeneralMapper<Enseignant, EnseignantDTO> mapperEnseignant = new GeneralMapper<>(EnseignantDTO.class, Enseignant.class);
    private final GeneralMapper<Departement, DepartementDTO> mapperDepartement = new GeneralMapper<>(DepartementDTO.class, Departement.class);
    //Mapper Classe and ElementDeModule
    private final GeneralMapper<Classe, ClasseDTO> mapperClasse = new GeneralMapper<>(ClasseDTO.class, Classe.class);
    private final GeneralMapper<ElementDeModule, ElementDeModuleDTO> mapperElementDeModule = new GeneralMapper<>(ElementDeModuleDTO.class, ElementDeModule.class);
    private final GeneralMapper<Semestre, SemestreDTO> mapperSemestre = new GeneralMapper<>(SemestreDTO.class, Semestre.class);
    private final GeneralMapper<TypeSalle, TypeSalleDTO> mapperTypeSalle = new GeneralMapper<>(TypeSalleDTO.class, TypeSalle.class);
    private final GeneralMapper<Filiere, FiliereDTO> mapperFiliere = new GeneralMapper<>(FiliereDTO.class, Filiere.class);
    private final GeneralMapper<Salle, SalleDTO> mapperSalle = new GeneralMapper<>(SalleDTO.class, Salle.class);

    private final GeneralMapper<Seance, SeanceDTO> mapperSeance = new GeneralMapper<>(SeanceDTO.class, Seance.class);

    private final GeneralMapper<Module, ModuleDTO> mapperModule = new GeneralMapper<>(ModuleDTO.class, Module.class);
    private final GeneralMapper<NonDisponibilite, NonDisponibiliteDTO> mapperNonDisponibilite = new GeneralMapper<>(NonDisponibiliteDTO.class, NonDisponibilite.class);


    //Constructor with allargs of service


    public ControllerGraphql(IDepartementService departementService, IEnseignantService enseignantService, IClasseService classeService, IElementDeModuleService elementDeModuleService, ISemestreService semestreService, ITypeSalleService typeSalleService, IFiliereService filiereService, ISalleService salleService, ISeanceService seanceService, IModuleService moduleService, INonDisponibiliteService nonDisponibiliteService) {


        this.departementService = departementService;
        this.enseignantService = enseignantService;
        this.classeService = classeService;
        this.elementDeModuleService = elementDeModuleService;
        this.typeSalleService = typeSalleService;
        this.semestreService = semestreService;
        this.seanceService = seanceService;
        this.filiereService = filiereService;
        this.salleService = salleService;
        this.moduleService = moduleService;
        this.nonDisponibiliteService = nonDisponibiliteService;
    }

    //**************** Departement *****************
    @QueryMapping
    public List<Departement> findDepartements() {
        return departementService.getDepartements();
    }

    @QueryMapping
    public Departement findDepartement(@Argument Long id) {
        return departementService.getDepartementById(id);
    }

    @MutationMapping
    public Departement addDepartement(@Argument DepartementDTO departement) {
        return departementService.addDepartement(mapperDepartement.fromRequestDTO(departement));
    }

    @MutationMapping
    public String deleteDepartement(@Argument Long id) {
        return departementService.deleteDepartement(id);
    }

    @MutationMapping
    public Departement updateDepartement(@Argument Long id, @Argument DepartementDTO departement) {
        return departementService.updateDeparetement(id, mapperDepartement.fromRequestDTO(departement));
    }

    //**************** Semestre *****************
    @QueryMapping
    public List<Semestre> findSemestres() {
        return semestreService.getSemestres();
    }

    @QueryMapping
    public Semestre findSemestre(@Argument Long id) {
        return semestreService.getSemestreById(id);
    }

    @MutationMapping
    public Semestre addSemestre(@Argument SemestreDTO semestre) {
        return semestreService.addSemestre(mapperSemestre.fromRequestDTO(semestre));
    }

    @MutationMapping
    public String deleteSemestre(@Argument Long id) {
        return semestreService.deleteSemestre(id);
    }

    @MutationMapping
    public Semestre updateSemestre(@Argument Long id, @Argument SemestreDTO semestre) {
        return semestreService.updateSemestre(id, mapperSemestre.fromRequestDTO(semestre));
    }

    //**************** Enseignant *****************
    @QueryMapping
    public List<Enseignant> findEnseignants() {
        return enseignantService.getEnseignants();
    }

    @QueryMapping
    public Enseignant findEnseignant(@Argument Long id) {
        return enseignantService.getEnseignantById(id);
    }

    @MutationMapping
    public Enseignant addEnseignant(@Argument EnseignantDTO enseignant) {
        return enseignantService.addEnseignant(mapperEnseignant.fromRequestDTO(enseignant));
    }

    @MutationMapping
    public String deleteEnseignant(@Argument Long id) {
        return enseignantService.deleteEnseignant(id);
    }

    @MutationMapping
    public Enseignant updateEnseignant(@Argument Long id, @Argument EnseignantDTO enseignant) {
        return enseignantService.updateEnseignant(id, mapperEnseignant.fromRequestDTO(enseignant));
    }

    //**************** TypeSalle *****************
    @QueryMapping
    public List<TypeSalle> findTypeSalles() {
        return typeSalleService.getTypeSalles();
    }

    @QueryMapping
    public TypeSalle findTypeSalle(@Argument Long id) {
        return typeSalleService.getTypeSalleById(id);
    }

    @MutationMapping
    public TypeSalle addTypeSalle(@Argument TypeSalleDTO typeSalle) {
        return typeSalleService.addTypeSalle(mapperTypeSalle.fromRequestDTO(typeSalle));
    }

    @MutationMapping
    public String deleteTypeSalle(@Argument Long id) {
        return typeSalleService.deleteTypeSalle(id);
    }

    @MutationMapping
    public TypeSalle updateTypeSalle(@Argument Long id, @Argument TypeSalleDTO typeSalle) {
        return typeSalleService.updateTypeSalle(id, mapperTypeSalle.fromRequestDTO(typeSalle));
    }

    //**************** put your methodes below *****************

    //**************** Classe *****************
    @QueryMapping
    public List<Classe> findClasses() {
        return classeService.getClasses();
    }

    // add all this methodes to the file schema.graphqls
    // and after test it using this http://localhost:8082/graphiql?path=/graphql

    @QueryMapping
    public Classe findClasse(@Argument Long id) {
        return classeService.getClasseById(id);
    }

    @MutationMapping
    public Classe addClasse(@Argument ClasseDTO classe) {
        return classeService.addClasse(mapperClasse.fromRequestDTO(classe));
    }

    @MutationMapping
    public String deleteClasse(@Argument Long id) {
        return classeService.deleteClasse(id);
    }

    @MutationMapping
    public Classe updateClasse(@Argument Long id, @Argument ClasseDTO classe) {
        return classeService.updateClasse(id, mapperClasse.fromRequestDTO(classe));
    }

    //**************** Element de Module *****************
    @QueryMapping
    public List<ElementDeModule> findElementsDeModule() {
        return elementDeModuleService.getElementDeModule();
    }

    @QueryMapping
    public ElementDeModule findElementDeModule(@Argument Long id) {
        return elementDeModuleService.getElementDeModuleById(id);
    }

    @MutationMapping
    public ElementDeModule addElementDeModule(@Argument ElementDeModuleDTO elementdemodule) {
        return elementDeModuleService.addElementDeModule(mapperElementDeModule.fromRequestDTO(elementdemodule));
    }

    @MutationMapping
    public String deleteElementDeModule(@Argument Long id) {
        return elementDeModuleService.deleteElementDeModule(id);
    }

    @MutationMapping
    public ElementDeModule updateElementDeModule(@Argument Long id, @Argument ElementDeModuleDTO elementdemodule) {
        return elementDeModuleService.updateElementDeModule(id, mapperElementDeModule.fromRequestDTO(elementdemodule));
    }

    //****** Filiere *******
    @QueryMapping
    public List<Filiere> findFilieres() {
        return filiereService.getFilieres();
    }

    @QueryMapping
    public Filiere findFiliere(@Argument Long id) {
        return filiereService.getFiliereById(id);
    }

    @MutationMapping
    public Filiere addFiliere(@Argument FiliereDTO filiere) {
        return filiereService.addFiliere(mapperFiliere.fromRequestDTO(filiere));
    }

    @MutationMapping
    public String deleteFiliere(@Argument Long id) {
        return filiereService.deleteFiliere(id);
    }

    @MutationMapping
    public Filiere updateFiliere(@Argument Long id, @Argument FiliereDTO filiere) {
        System.out.println("Filiere");
        return filiereService.updateFiliere(id, mapperFiliere.fromRequestDTO(filiere));
    }

    //****** Salle *******
    @QueryMapping
    public List<Salle> findSalles() {
        return salleService.getSalles();
    }

    @QueryMapping
    public Salle findSalle(@Argument Long id) {
        return salleService.getSalleById(id);
    }

    @MutationMapping
    public Salle addSalle(@Argument SalleDTO salle) {
        return salleService.addSalle(mapperSalle.fromRequestDTO(salle));
    }

    @MutationMapping
    public String deleteSalle(@Argument Long id) {
        return salleService.deleteSalle(id);
    }

    @MutationMapping
    public Salle updateSalle(@Argument Long id, @Argument SalleDTO salle) {
        System.out.println("Salle");
        return salleService.updateSalle(id, mapperSalle.fromRequestDTO(salle));
    }


    //**************** Seance *****************
    @QueryMapping
    public List<Seance> findSeances() {
        return seanceService.getSeances();
    }

    @QueryMapping
    public Seance findSeance(@Argument Long id) {
        return seanceService.getSeanceById(id);
    }

    @MutationMapping
    public Seance addSeance(@Argument SeanceDTO seance) {
        return seanceService.addSeance(mapperSeance.fromRequestDTO(seance));
    }

    @MutationMapping
    public String deleteSeance(@Argument Long id) {
        return seanceService.deleteSeance(id);
    }

    @MutationMapping
    public Seance updateSeance(@Argument Long id, @Argument SeanceDTO seance) {
        return seanceService.updateSeance(id, mapperSeance.fromRequestDTO(seance));
    }

    //******************Module************
    @QueryMapping
    public List<Module> findModules() {
        return moduleService.getModules();
    }

    @QueryMapping
    public Module findModule(@Argument Long id) {
        return moduleService.getModuleById(id);
    }

    @MutationMapping
    public Module addModule(@Argument ModuleDTO module) {
        return moduleService.addModule(mapperModule.fromRequestDTO(module));
    }

    @MutationMapping
    public String deleteModule(@Argument Long id) {
        return moduleService.deleteModule(id);
    }

    @MutationMapping
    public Module updateModule(@Argument Long id, @Argument ModuleDTO module) {
        return moduleService.updateModule(id, mapperModule.fromRequestDTO(module));
    }

    //*********************NonDisponibilite******************
    @QueryMapping
    public List<NonDisponibilite> findNonDisponibilites() {
        return nonDisponibiliteService.getNonDisponibilites();
    }

    @QueryMapping
    public NonDisponibilite findNonDisponibilite(@Argument Long id) {
        return nonDisponibiliteService.getNonDisponibiliteById(id);
    }

    @MutationMapping
    public NonDisponibilite addNonDisponibilite(@Argument NonDisponibiliteDTO nonDisponibilite) {
        return nonDisponibiliteService.addNonDisponibilite(mapperNonDisponibilite.fromRequestDTO(nonDisponibilite));
    }

    @MutationMapping
    public String deleteNonDisponibilite(@Argument Long id) {
        return nonDisponibiliteService.deleteNonDisponibilite(id);
    }

    @MutationMapping
    public NonDisponibilite updateNonDisponibilite(@Argument Long id, @Argument NonDisponibiliteDTO nonDisponibilite) {
        return nonDisponibiliteService.updateNonDisponibilite(id, mapperNonDisponibilite.fromRequestDTO(nonDisponibilite));
    }
}


