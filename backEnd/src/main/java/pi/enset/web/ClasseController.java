package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Classe;
import pi.enset.services.IClasseService;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/classes")
@AllArgsConstructor
public class ClasseController {
    private final IClasseService classeService;
    @GetMapping
    public Page<Classe> getAllClasses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return classeService.getClasses(pageable);
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

    @GetMapping("/search")
    public Page<Classe> searchClasses(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return classeService.searchClasses(keyword, pageable);
    }
    @GetMapping("/searchSem")
    public Page<Classe> searchClassesSem(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam Long sem
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return classeService.searchClasses(keyword,sem, pageable);
    }
}
