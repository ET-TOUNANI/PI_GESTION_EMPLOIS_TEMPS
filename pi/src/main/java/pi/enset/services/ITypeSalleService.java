package pi.enset.services;


import pi.enset.entities.TypeSalle;

import java.util.List;

public interface ITypeSalleService {
    List<TypeSalle> getTypeSalles();

    TypeSalle addTypeSalle(TypeSalle typeSalle);

    String deleteTypeSalle(Long id);

    TypeSalle getTypeSalleById(Long id);

    TypeSalle updateTypeSalle(Long id, TypeSalle typeSalle);

}

