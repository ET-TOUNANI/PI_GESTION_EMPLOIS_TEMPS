package pi.enset.services;

import pi.enset.entities.Module;

import java.util.List;

public interface IModuleService {
    List<Module> getModules();

    Module addModule(Module module);

    String deleteModule(Long id);

    Module getModuleById(Long id);

    Module updateModule(Long id, Module module);
}
