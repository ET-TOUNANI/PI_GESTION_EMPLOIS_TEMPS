package pi.enset.services;

import pi.enset.entities.Semestre;

import java.util.List;

public interface ISemestreService {
    List<Semestre> getSemestres();

    Semestre addSemestre(Semestre semestre);

    String deleteSemestre(Long id);

    Semestre getSemestreById(Long id);

    Semestre updateSemestre(Long id, Semestre semestre);
<<<<<<< HEAD
}
=======
}
>>>>>>> d47801b397487acc5b35bf0f7853f2b97a87ccb5
