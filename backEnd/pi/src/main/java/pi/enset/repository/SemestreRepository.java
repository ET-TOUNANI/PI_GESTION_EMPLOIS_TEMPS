package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Semestre;

public interface SemestreRepository extends JpaRepository<Semestre, Long> {
}
