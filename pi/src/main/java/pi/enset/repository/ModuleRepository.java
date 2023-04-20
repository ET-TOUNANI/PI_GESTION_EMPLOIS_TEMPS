package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
