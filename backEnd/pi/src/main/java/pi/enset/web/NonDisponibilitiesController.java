package pi.enset.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.NonDisponibilite;
import pi.enset.services.INonDisponibiliteService;

import java.util.List;

@RestController
@RequestMapping("/api/nonDisponibilites")
@AllArgsConstructor
public class NonDisponibilitiesController {

    private final INonDisponibiliteService nonDisponibiliteService;


    @GetMapping
    public List<NonDisponibilite> getAllNonDisponibilites() {
        return nonDisponibiliteService.getNonDisponibilites();
    }

    @GetMapping("/{id}")
    public NonDisponibilite getNonDisponibiliteById(@PathVariable Long id) {
        return nonDisponibiliteService.getNonDisponibiliteById(id);
    }

    @PostMapping
    public NonDisponibilite createNonDisponibilite(@RequestBody NonDisponibilite nonDisponibilite) {
        return nonDisponibiliteService.addNonDisponibilite(nonDisponibilite);
    }

    @PutMapping("/{id}")
    public NonDisponibilite updateNonDisponibilite(@PathVariable Long id, @RequestBody NonDisponibilite updatedNonDisponibilite) {
        return nonDisponibiliteService.updateNonDisponibilite(id, updatedNonDisponibilite);
    }

    @DeleteMapping("/{id}")
    public String deleteNonDisponibilite(@PathVariable Long id) {
        return nonDisponibiliteService.deleteNonDisponibilite(id);
    }
}
