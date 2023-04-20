package pi.enset.web;


import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pi.enset.DTO.DepartementDTO;
import pi.enset.DTO.EnseignantDTO;
import pi.enset.DTO.mappers.GeneralMapper;
import pi.enset.entities.Departement;
import pi.enset.entities.Enseignant;
import pi.enset.services.IDepartementService;
import pi.enset.services.IEnseignantService;

import java.util.List;

@Controller
public class ControllerGraphql {
    // don't forget to add your service here
    private final IDepartementService departementService;
    private final IEnseignantService enseignantService;
    // don't forget to custemize your mapper here
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
    //**************** put your methodes below *****************


}