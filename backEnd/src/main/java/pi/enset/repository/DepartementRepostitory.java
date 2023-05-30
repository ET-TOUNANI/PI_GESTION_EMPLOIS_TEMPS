package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Departement;

import java.util.List;

public interface DepartementRepostitory extends JpaRepository<Departement, Long> {
    List<Departement>  findDepartementByLibelle(String nom);
}
