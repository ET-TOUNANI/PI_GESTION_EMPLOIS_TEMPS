package pi.enset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pi.enset.entities.Enseignant;
import pi.enset.entities.Person;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    @Query("SELECT u FROM Person u WHERE u.Role = ?1")
    Page<Enseignant> findUsersByRole(String role, Pageable pageable);


    @Query("SELECT e FROM Person e WHERE (e.nom LIKE %?1% OR e.prenom LIKE %?1%) AND e.Role = 'PROF'")
    Page<Enseignant> searchWithPagination(String keyword, Pageable pageable);

    @Query("SELECT u FROM Person u WHERE u.Role = ?1")
    List<Enseignant> findAllByRole(String role);
    @Query("SELECT u FROM Person u WHERE u.Role = 'PROF' AND (u.nom LIKE %?1% OR u.prenom LIKE %?1%)")
    List<Enseignant> findEnseignantByNom(String nom);


    @Query("SELECT u FROM Person u WHERE u.login = ?1 AND u.password = ?2")
    Person findByLoginAndPassword(String login, String password);
}

