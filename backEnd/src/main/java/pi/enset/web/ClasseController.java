package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Classe;
import pi.enset.services.IClasseService;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/classes")
@AllArgsConstructor
public class ClasseController {

    private final IClasseService classeService;


    @GetMapping
    public List<Classe> getAllClasses() {
        return classeService.getClasses();
    }

    @GetMapping("/{id}")
    public Classe getClasseById(@PathVariable Long id) {
        return classeService.getClasseById(id);
    }

    @PostMapping
    public Classe createClasse(@RequestBody Classe classe) {
        return classeService.addClasse(classe, 1L);
    }

    @PutMapping("/{id}")
    public Classe updateClasse(@PathVariable Long id, @RequestBody Classe updatedClasse) {
        return classeService.updateClasse(id, updatedClasse);
    }

    @DeleteMapping("/{id}")
    public String deleteClasse(@PathVariable Long id) {
        return classeService.deleteClasse(id);
    }
}
