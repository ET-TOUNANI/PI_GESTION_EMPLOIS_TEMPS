package pi.enset.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pi.enset.entities.Classe;

import java.util.List;

public interface IClasseService {
    List<Classe> getClasses();

    Classe addClasse(Classe classe, Long idFielre);

    String deleteClasse(Long id);

    Classe getClasseById(Long id);

    Classe updateClasse(Long id, Classe classe);
    Page<Classe> getClasses(Pageable pageable);
    Page<Classe> searchClasses(String keyword, Pageable pageable);
    Page<Classe> searchClasses(String keyword,Long sem, Pageable pageable);
}
