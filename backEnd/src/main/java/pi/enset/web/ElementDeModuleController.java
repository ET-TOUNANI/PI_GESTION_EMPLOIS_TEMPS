package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.ElementDeModule;
import pi.enset.services.IElementDeModuleService;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/api/elementModules")
@AllArgsConstructor
public class ElementDeModuleController {
    private final IElementDeModuleService elementDeModuleService;

    @GetMapping
    public List<ElementDeModule> getAllElements() {
        return elementDeModuleService.getElementDeModule();
    }

    @GetMapping("/{id}")
    public ElementDeModule getElementById(@PathVariable Long id) {
        return elementDeModuleService.getElementDeModuleById(id);
    }

    @PostMapping
    public ElementDeModule createElement(@RequestBody ElementDeModule element) {
        return elementDeModuleService.addElementDeModule(element);
    }

    @PutMapping("/{id}")
    public ElementDeModule updateElement(@PathVariable Long id, @RequestBody ElementDeModule updatedElement) {
        return elementDeModuleService.updateElementDeModule(id, updatedElement);
    }

    @DeleteMapping("/{id}")
    public String deleteElement(@PathVariable Long id) {
        return elementDeModuleService.deleteElementDeModule(id);
    }


}
