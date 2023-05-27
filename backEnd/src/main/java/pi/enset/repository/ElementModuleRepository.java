package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pi.enset.entities.ElementDeModule;

import java.util.List;

public interface ElementModuleRepository extends JpaRepository<ElementDeModule, Long> {
    @Query("select e from ElementDeModule e where e.module.classe.id = ?1")
    List<ElementDeModule> getEmploisByClasse(Long classeId);
}
