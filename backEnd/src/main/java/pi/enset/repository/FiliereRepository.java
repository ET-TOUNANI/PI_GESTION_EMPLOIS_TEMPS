package pi.enset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pi.enset.entities.Filiere;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    @Query("select e from Filiere e where e.libelle LIKE %?1% ")
    Page<Filiere> searchFilieres(String keyword, Pageable pageable);
}
