package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.NonDisponibilite;

public interface NonDisponibiliteRepository extends JpaRepository<NonDisponibilite, Long> {
}
