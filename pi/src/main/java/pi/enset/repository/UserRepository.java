package pi.enset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.enset.entities.Classe;
import pi.enset.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
