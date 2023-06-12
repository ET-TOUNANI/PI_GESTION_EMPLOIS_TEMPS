package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Semestre;
import pi.enset.entities.enums.NumeroSemester;

import java.util.List;

public interface SemestreRepository extends JpaRepository<Semestre, Long> {
    List<Semestre> findSemestreByNum(NumeroSemester numeroSemester);
}
