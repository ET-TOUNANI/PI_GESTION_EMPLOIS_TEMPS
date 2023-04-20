package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
}
