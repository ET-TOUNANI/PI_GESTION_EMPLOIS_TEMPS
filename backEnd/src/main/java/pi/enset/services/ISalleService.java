package pi.enset.services;

import pi.enset.entities.Salle;

import java.util.List;

public interface ISalleService {
    List<Salle> getSalles();

    Salle addSalle(Salle salle);

    String deleteSalle(Long id);

    Salle getSalleById(Long id);

    Salle updateSalle(Long id, Salle salle);

}
