package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pi.enset.entities.Enseignant;

import java.util.List;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    @Query("SELECT e FROM Enseignant e WHERE e.nom LIKE %?1% OR e.prenom LIKE %?1% OR e.specialite LIKE %?1%")
    List<Enseignant> search(String keyword);
}
