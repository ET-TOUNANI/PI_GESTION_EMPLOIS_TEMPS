package pi.enset.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pi.enset.entities.Departement;
import pi.enset.entities.Filiere;

import java.util.List;

public interface IDepartementService {
    List<Departement> getDepartements();

    Departement addDepartement(Departement departement);
    List<Departement> findDepartementByNom(String nom);

    String deleteDepartement(Long id);

    Departement getDepartementById(Long id);

    Departement updateDeparetement(Long id, Departement departement);

    Page<Departement> searchDepartements(String keyword, Pageable pageable);

    List<Filiere> getFilieresByDepartmentId(Long id);
}
