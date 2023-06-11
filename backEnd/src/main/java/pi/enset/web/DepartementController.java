package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Departement;
import pi.enset.entities.Filiere;
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
    @GetMapping("/search")
    public Page<Departement> searchDepartements(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return departementService.searchDepartements(keyword, pageable);
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
    @GetMapping("/{id}/filieres")
    public List<Filiere> getFilieresByDepartmentId(@PathVariable Long id) {
        return departementService.getFilieresByDepartmentId(id);
    }
}
