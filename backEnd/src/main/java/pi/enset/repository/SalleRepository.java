package pi.enset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pi.enset.entities.Salle;
import pi.enset.entities.enums.TypeSalle;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    @Query("SELECT e FROM Salle e WHERE e.typeSalle = ?1")
    Page<Salle> searchWithPagination(TypeSalle keyword, Pageable pageable);
}
