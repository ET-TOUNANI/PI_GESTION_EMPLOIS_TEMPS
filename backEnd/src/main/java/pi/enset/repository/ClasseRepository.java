package pi.enset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pi.enset.entities.Classe;
import pi.enset.entities.ElementDeModule;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    @Query("select e from Classe e where e.filiere.departement.id = ?1")
    List<Classe> getClassesByDepartement(Long departementId);

    @Query("select e from Classe e where e.id = ?1")
    Classe getClassesByID(Long Id);
    @Query("select e from Classe e where e.semestre.id=?2 and (e.libelle LIKE %?1%  or e.filiere.libelle LIKE %?1% )")
    Page<Classe> searchClasses(String keyword,Long sem, Pageable pageable);
    @Query("select e from Classe e where e.libelle LIKE %?1%  or e.filiere.libelle LIKE %?1% ")
    Page<Classe> searchClasses(String keyword, Pageable pageable);
}
