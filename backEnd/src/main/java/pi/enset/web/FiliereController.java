package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Filiere;
import pi.enset.services.IFiliereService;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/filieres")
@AllArgsConstructor
public class FiliereController {

    private final IFiliereService filiereService;


    @GetMapping
    public List<Filiere> getAllFilieres() {
        return filiereService.getFilieres();
    }

    @GetMapping("/{id}")
    public Filiere getFiliereById(@PathVariable Long id) {
        return filiereService.getFiliereById(id);
    }

    @PostMapping
    public Filiere createFiliere(@RequestBody Filiere filiere) {
        return filiereService.addFiliere(filiere);
    }

    @PutMapping("/{id}")
    public Filiere updateFiliere(@PathVariable Long id, @RequestBody Filiere updatedFiliere) {
        return filiereService.updateFiliere(id, updatedFiliere);
    }


    @DeleteMapping("/{id}")
    public String deleteFiliere(@PathVariable Long id) {
        return filiereService.deleteFiliere(id);
    }
}
