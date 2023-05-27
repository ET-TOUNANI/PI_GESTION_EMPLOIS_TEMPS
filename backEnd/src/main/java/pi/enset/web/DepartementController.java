package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Departement;
import pi.enset.services.IDepartementService;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/departements")
@AllArgsConstructor
public class DepartementController {

    private final IDepartementService departementService;


    @GetMapping
    public List<Departement> getAllDepartements() {
        return departementService.getDepartements();
    }

    @GetMapping("/{id}")
    public Departement getDepartementById(@PathVariable Long id) {
        return departementService.getDepartementById(id);
    }

    @PostMapping
    public Departement createDepartement(@RequestBody Departement departement) {
        return departementService.addDepartement(departement);
    }

    @PutMapping("/{id}")
    public Departement updateDepartement(@PathVariable Long id, @RequestBody Departement updatedDepartement) {
        return departementService.updateDeparetement(id, updatedDepartement);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartement(@PathVariable Long id) {
        return departementService.deleteDepartement(id);
    }
}
