package pi.enset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pi.enset.entities.Departement;
import pi.enset.entities.Filiere;

import java.util.List;

public interface DepartementRepostitory extends JpaRepository<Departement, Long> {
    List<Departement>  findDepartementByLibelle(String nom);
    @Query("SELECT e FROM Departement e WHERE e.libelle LIKE %?1%")

    Page<Departement> searchWithPagination(String keyword, Pageable pageable);


    @Query("SELECT e.filieres FROM Departement e WHERE e.id = ?1")
    List<Filiere> getFilieresByDepartmentId(Long id);
}
