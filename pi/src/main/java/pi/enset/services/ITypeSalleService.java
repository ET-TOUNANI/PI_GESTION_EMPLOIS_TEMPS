package pi.enset.services;


import pi.enset.entities.Departement;
import pi.enset.entities.Salle;
import pi.enset.entities.TypeSalle;

import java.util.List;

public interface ITypeSalleService {
    List<TypeSalle> getTypeSalles();

    TypeSalle addTypeSalle(TypeSalle typeSalle);

    String deleteTypeSalle(Long id);

    TypeSalle getTypeSalleById(Long id);

    TypeSalle updateTypeSalle(Long id, TypeSalle typeSalle);
<<<<<<< HEAD
}
=======
}
>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5
