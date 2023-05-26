package pi.enset.services;

import pi.enset.entities.ElementDeModule;

import java.util.List;

public interface IElementDeModuleService {
    List<ElementDeModule> getElementDeModule();

    ElementDeModule addElementDeModule(ElementDeModule elementDeModule);

    String deleteElementDeModule(Long id);

    ElementDeModule getElementDeModuleById(Long id);

    ElementDeModule updateElementDeModule(Long id, ElementDeModule elementDeModule);

    List<ElementDeModule> getEmploisByClasse(Long classeId);
}
