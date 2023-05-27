package pi.enset.services;


import pi.enset.entities.Departement;

import java.util.List;

public interface IDepartementService {
    List<Departement> getDepartements();

    Departement addDepartement(Departement departement);

    String deleteDepartement(Long id);

    Departement getDepartementById(Long id);

    Departement updateDeparetement(Long id, Departement departement);
}
