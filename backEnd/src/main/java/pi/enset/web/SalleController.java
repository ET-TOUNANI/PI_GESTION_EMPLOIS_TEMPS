package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Salle;
import pi.enset.services.ISalleService;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/salles")
@AllArgsConstructor
public class SalleController {

    private final ISalleService salleService;


    @GetMapping
    public List<Salle> getAllSalles() {
        return salleService.getSalles();
    }

    @GetMapping("/{id}")
    public Salle getSalleById(@PathVariable Long id) {
        return salleService.getSalleById(id);
    }

    @PostMapping
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.addSalle(salle);
    }

    @PutMapping("/{id}")
    public Salle updateSalle(@PathVariable Long id, @RequestBody Salle updatedSalle) {
        return salleService.updateSalle(id, updatedSalle);
    }

    @DeleteMapping("/{id}")
    public String deleteSalle(@PathVariable Long id) {
        return salleService.deleteSalle(id);
    }
}
