package pi.enset.services;

import pi.enset.entities.Seance;

import java.util.List;

public interface ISeanceService {

    List<Seance> getSeances();

    Seance addSeance(Seance seance);

    String deleteSeance(Long id);

    Seance getSeanceById(Long id);

    Seance updateSeance(Long id, Seance seance);

}
