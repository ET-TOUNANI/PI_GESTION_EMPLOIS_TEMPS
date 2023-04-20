package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Seance;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
}
