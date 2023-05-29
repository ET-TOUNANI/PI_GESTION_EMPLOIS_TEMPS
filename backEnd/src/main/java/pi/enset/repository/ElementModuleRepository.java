package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pi.enset.entities.ElementDeModule;
import pi.enset.entities.enums.Periode;

import java.time.DayOfWeek;
import java.util.List;

public interface ElementModuleRepository extends JpaRepository<ElementDeModule, Long> {
    @Query("select e from ElementDeModule e where e.module.classe.id = ?1")
    List<ElementDeModule> getEmploisByClasse(Long classeId);
    @Query("SELECT e FROM ElementDeModule e WHERE e.jour = :dayOfWeek AND e.periode = :periode")
    ElementDeModule findByDayOfWeekAndPeriode(@Param("dayOfWeek") DayOfWeek dayOfWeek, @Param("periode") Periode periode);

    @Query("SELECT e FROM ElementDeModule e WHERE e.jour = :dayOfWeek AND e.periode = :periode AND e.module.classe.id = :classeId")
    List<ElementDeModule> findByDayOfWeekAndPeriodeAndClasse(@Param("dayOfWeek") DayOfWeek dayOfWeek, @Param("periode") Periode periode, @Param("classeId") Long classeId);

    @Query("SELECT e FROM ElementDeModule e WHERE e.jour = :dayOfWeek AND e.periode = :periode AND e.enseignant.id = :ProfId")
    List<ElementDeModule> findByDayOfWeekAndPeriodeAndProf(@Param("dayOfWeek") DayOfWeek dayOfWeek, @Param("periode") Periode periode, @Param("ProfId") Long ProfId);




}
