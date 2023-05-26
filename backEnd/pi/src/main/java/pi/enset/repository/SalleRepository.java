package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long> {
}
