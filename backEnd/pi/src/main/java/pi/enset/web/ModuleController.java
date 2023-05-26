package pi.enset.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pi.enset.entities.Module;
import pi.enset.services.IModuleService;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/modules")
public class ModuleController {
    private final IModuleService moduleService;


    @GetMapping
    public List<Module> getAllModules() {
        return moduleService.getModules();
    }

    @GetMapping("/{id}")
    public Module getModuleById(@PathVariable Long id) {
        return moduleService.getModuleById(id);
    }

    @PostMapping
    public Module createModule(@RequestBody Module module) {
        return moduleService.addModule(module);
    }

    @PutMapping("/{id}")
    public Module updateModule(@PathVariable Long id, @RequestBody Module updatedModule) {
        return moduleService.updateModule(id, updatedModule);
    }

    @DeleteMapping("/{id}")
    public String deleteModule(@PathVariable Long id) {
        return moduleService.deleteModule(id);
    }
}
