package pi.enset.web;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import pi.enset.DTO.*;
import pi.enset.DTO.mappers.GeneralMapper;
import pi.enset.entities.*;
import pi.enset.repository.ClasseRepository;
import pi.enset.services.*;
=======
import pi.enset.DTO.DepartementDTO;
import pi.enset.DTO.EnseignantDTO;
import pi.enset.DTO.SemestreDTO;
import pi.enset.DTO.TypeSalleDTO;
import pi.enset.DTO.mappers.GeneralMapper;
import pi.enset.entities.Departement;
import pi.enset.entities.Enseignant;
import pi.enset.entities.Semestre;
import pi.enset.entities.TypeSalle;
import pi.enset.services.IDepartementService;
import pi.enset.services.IEnseignantService;
import pi.enset.services.ISemestreService;
import pi.enset.services.ITypeSalleService;
<<<<<<< HEAD
>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5
=======
>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5

import javax.swing.text.Element;
import java.util.List;

@Controller
public class ControllerGraphql {
    // don't forget to add your service here

private ISemestreService semestreService;
    private ITypeSalleService typeSalleService;
    private final IDepartementService departementService;
    private final IEnseignantService enseignantService;
<<<<<<< HEAD
    //Classe and ElementModule
    private final IClasseService ClasseService;
    private final IElementDeModuleService ElementDeModuleService;
    private ISemestreService semestreService;
    private ITypeSalleService typeSalleService;
    //Repositories
    private ClasseRepository classeRepository;
    // don't forget to customize your mapper here
=======
    // don't forget to custemize your mapper here
    private GeneralMapper<Semestre, SemestreDTO> mapperSemestre=new GeneralMapper<>(SemestreDTO.class,Semestre.class);
    private GeneralMapper<TypeSalle, TypeSalleDTO> mapperTypeSalle=new GeneralMapper<>(TypeSalleDTO.class,TypeSalle.class);
<<<<<<< HEAD
>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5
=======
>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5
    private final GeneralMapper<Enseignant, EnseignantDTO> mapperEnseignant=new GeneralMapper<>(EnseignantDTO.class,Enseignant.class);
    private final GeneralMapper<Departement, DepartementDTO> mapperDepartement=new GeneralMapper<>(DepartementDTO.class,Departement.class);
    //Mapper Classe and ElementDeModule
    private final GeneralMapper<Classe, ClasseDTO> mapperClasse=new GeneralMapper<>(ClasseDTO.class,Classe.class);
    private final GeneralMapper<ElementDeModule, ElementDeModuleDTO> mapperElementDeModule =new GeneralMapper<>(ElementDeModuleDTO.class,ElementDeModule.class);
    private GeneralMapper<Semestre, SemestreDTO> mapperSemestre;
    private GeneralMapper<TypeSalle, TypeSalleDTO> mapperTypeSalle;
    /*
    public ControllerGraphql(IDepartementService departementService, IEnseignantService enseignantService) {
        this.departementService = departementService;
        this.enseignantService = enseignantService;
    }
    */
    //Constructor with allargs of service


    public ControllerGraphql(IDepartementService departementService, IEnseignantService enseignantService, IClasseService classeService, IElementDeModuleService elementDeModuleService) {
        this.departementService = departementService;
        this.enseignantService = enseignantService;
        ClasseService = classeService;
        ElementDeModuleService = elementDeModuleService;
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
    //**************** enseignant *****************
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
    public TypeSalle updateTypeSalle(@Argument Long id, @Argument TypeSalleDTO typeSalleDTO) {
        return typeSalleService.updateTypeSalle(id, mapperTypeSalle.fromRequestDTO(typeSalleDTO));
    }
    //**************** put your methodes below *****************
<<<<<<< HEAD
<<<<<<< HEAD
    //**************** Classe *****************
    @QueryMapping
    public List<Classe>  findClasses() {
        return ClasseService.getClasses();
    }
=======
    // add all this methodes to the file schema.graphqls
    // and after test it using this http://localhost:8082/graphiql?path=/graphql
=======
    // add all this methodes to the file schema.graphqls
    // and after test it using this http://localhost:8082/graphiql?path=/graphql

}
>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5

}
>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5

    @QueryMapping
    public Classe findClasse(@Argument Long id) {
        return ClasseService.getClasseById(id);
    }

    @MutationMapping
    public Classe addClasse(@Argument ClasseDTO classe) {
        return ClasseService.addClasse(mapperClasse.fromRequestDTO(classe));
    }

    @MutationMapping
    public String deleteClasse(@Argument Long id) {
        return ClasseService.deleteClasse(id);
    }

    @MutationMapping
    public Classe updateClasse(@Argument Long id, @Argument ClasseDTO classe) {
        return ClasseService.updateClasse(id, mapperClasse.fromRequestDTO(classe));
    }
    //**************** Element de Module *****************
    @QueryMapping
    public List<ElementDeModule>  findElementsDeModule() {
        return ElementDeModuleService.getElementDeModule();
    }

    @QueryMapping
    public ElementDeModule findElementDeModule(@Argument Long id) {
        return ElementDeModuleService.getElementDeModuleById(id);
    }

    @MutationMapping
    public ElementDeModule addElementDeModule(@Argument ElementDeModuleDTO elementdemodule) {
        return ElementDeModuleService.addElementDeModule(mapperElementDeModule.fromRequestDTO(elementdemodule));
    }

    @MutationMapping
    public String deleteElementDeModule(@Argument Long id) {
        return ElementDeModuleService.deleteElementDeModule(id);
    }

    @MutationMapping
    public ElementDeModule updateElementDeModule(@Argument Long id, @Argument ElementDeModuleDTO elementdemodule) {
        return ElementDeModuleService.updateElementDeModule(id, mapperElementDeModule.fromRequestDTO(elementdemodule));
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
    public TypeSalle updateTypeSalle(@Argument Long id, @Argument TypeSalleDTO typeSalleDTO) {
        return typeSalleService.updateTypeSalle(id, mapperTypeSalle.fromRequestDTO(typeSalleDTO));
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

