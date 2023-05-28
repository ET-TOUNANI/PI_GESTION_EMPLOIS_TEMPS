package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Semestre;
import pi.enset.services.ISemestreService;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/semestres")
@AllArgsConstructor
public class SemseterController {

    private final ISemestreService semestreService;


    @GetMapping
    public List<Semestre> getAllSemestres() {
        return semestreService.getSemestres();
    }

    @GetMapping("/{id}")
    public Semestre getSemestreById(@PathVariable Long id) {
        return semestreService.getSemestreById(id);
    }

    @PostMapping
    public Semestre createSemestre(@RequestBody Semestre semestre) {
        return semestreService.addSemestre(semestre);
    }

    @PutMapping("/{id}")
    public Semestre updateSemestre(@PathVariable Long id, @RequestBody Semestre updatedSemestre) {
        return semestreService.updateSemestre(id, updatedSemestre);
    }

    @DeleteMapping("/{id}")
    public String deleteSemestre(@PathVariable Long id) {
        return semestreService.deleteSemestre(id);
    }
}
