package pi.enset.services;

import pi.enset.entities.NonDisponibilite;

import java.util.List;

public interface INonDisponibiliteService {
    List<NonDisponibilite> getNonDisponibilites();

    NonDisponibilite addNonDisponibilite(NonDisponibilite nonDisponibilite);

    String deleteNonDisponibilite(Long id);

    NonDisponibilite getNonDisponibiliteById(Long id);

    NonDisponibilite updateNonDisponibilite(Long id, NonDisponibilite nonDisponibilite);
}
