package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Departement;

public interface DepartementRepostitory extends JpaRepository<Departement, Long> {
}
