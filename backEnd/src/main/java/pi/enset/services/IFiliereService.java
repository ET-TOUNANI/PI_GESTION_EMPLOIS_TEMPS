package pi.enset.services;

import pi.enset.entities.Filiere;

import java.util.List;

public interface IFiliereService {

    List<Filiere> getFilieres();

    Filiere addFiliere(Filiere filiere);

    String deleteFiliere(Long id);

    Filiere getFiliereById(Long id);

    Filiere updateFiliere(Long id, Filiere filiere);
}
