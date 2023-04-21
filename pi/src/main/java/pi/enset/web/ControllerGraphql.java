package pi.enset.web;


import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
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

import java.util.List;

@Controller
public class ControllerGraphql {
    // don't forget to add your service here

private ISemestreService semestreService;
    private ITypeSalleService typeSalleService;
    private final IDepartementService departementService;
    private final IEnseignantService enseignantService;
    // don't forget to custemize your mapper here
    private GeneralMapper<Semestre, SemestreDTO> mapperSemestre=new GeneralMapper<>(SemestreDTO.class,Semestre.class);
    private GeneralMapper<TypeSalle, TypeSalleDTO> mapperTypeSalle=new GeneralMapper<>(TypeSalleDTO.class,TypeSalle.class);
    private final GeneralMapper<Enseignant, EnseignantDTO> mapperEnseignant=new GeneralMapper<>(EnseignantDTO.class,Enseignant.class);
    private final GeneralMapper<Departement, DepartementDTO> mapperDepartement=new GeneralMapper<>(DepartementDTO.class,Departement.class);

    public ControllerGraphql(IDepartementService departementService, IEnseignantService enseignantService) {
        this.departementService = departementService;
        this.enseignantService = enseignantService;
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
    // add all this methodes to the file schema.graphqls
    // and after test it using this http://localhost:8082/graphiql?path=/graphql

}


