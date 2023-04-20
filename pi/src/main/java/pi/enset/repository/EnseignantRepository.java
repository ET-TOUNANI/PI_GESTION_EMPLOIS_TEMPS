package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
}
