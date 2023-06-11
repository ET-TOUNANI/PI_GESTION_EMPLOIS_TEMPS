package pi.enset.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pi.enset.entities.Filiere;
import pi.enset.entities.Semestre;

import java.util.List;

public interface IFiliereService {

    List<Filiere> getFilieres();

    Filiere addFiliere(Filiere filiere);

    String deleteFiliere(Long id);

    Filiere getFiliereById(Long id);

    Filiere updateFiliere(Long id, Filiere filiere);
    Page<Filiere> getFilieres(Pageable pageable);
    Page<Filiere> searchFilieres(String keyword, Pageable pageable);
    List<Semestre> getSemestersByFiliere(Long filiereId);

}
