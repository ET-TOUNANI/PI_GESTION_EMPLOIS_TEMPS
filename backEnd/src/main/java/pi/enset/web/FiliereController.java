package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Filiere;
import pi.enset.entities.Semestre;
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
    public Page<Filiere> getAllFilieres(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return filiereService.getFilieres(pageable);
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

    @GetMapping("/search")
    public Page<Filiere> searchFilieres(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return filiereService.searchFilieres(keyword, pageable);
    }
    @GetMapping("/{id}/semesters")
    public List<Semestre> getSemestersByFiliere(@PathVariable Long id) {
        return filiereService.getSemestersByFiliere(id);
    }

}
