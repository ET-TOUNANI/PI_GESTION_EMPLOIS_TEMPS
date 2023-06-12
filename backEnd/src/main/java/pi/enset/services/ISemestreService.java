package pi.enset.services;

import pi.enset.entities.Semestre;
import pi.enset.entities.enums.NumeroSemester;

import java.util.List;

public interface ISemestreService {
    List<Semestre> getSemestres();

    Semestre addSemestre(Semestre semestre);

    String deleteSemestre(Long id);

    Semestre getSemestreById(Long id);

    Semestre updateSemestre(Long id, Semestre semestre);
    List<Semestre>  findSemestreByNum(NumeroSemester numeroSemester);
}

