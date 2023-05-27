package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Enseignant;
import pi.enset.services.IEnseignantService;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/enseignants")
@AllArgsConstructor
public class EnseignantController {

    private final IEnseignantService enseignantService;


    @GetMapping
    public List<Enseignant> getAllEnseignants() {
        return enseignantService.getEnseignants();
    }

    @GetMapping("/{id}")
    public Enseignant getEnseignantById(@PathVariable Long id) {
        return enseignantService.getEnseignantById(id);
    }

    @PostMapping
    public Enseignant createEnseignant(@RequestBody Enseignant enseignant) {
        return enseignantService.addEnseignant(enseignant);
    }

    @PutMapping("/{id}")
    public Enseignant updateEnseignant(@PathVariable Long id, @RequestBody Enseignant updatedEnseignant) {
        return enseignantService.updateEnseignant(id, updatedEnseignant);
    }

    @DeleteMapping("/{id}")
    public String deleteEnseignant(@PathVariable Long id) {
        return enseignantService.deleteEnseignant(id);
    }
    // search?keyword=keyword
    @GetMapping("/search")
    public List<Enseignant> searchEnseignants(@RequestParam String keyword) {
        return enseignantService.searchEnseignants(keyword);
    }
}
