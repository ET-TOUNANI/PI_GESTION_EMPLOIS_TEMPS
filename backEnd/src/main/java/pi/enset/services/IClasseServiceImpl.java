package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pi.enset.entities.Classe;
import pi.enset.entities.Filiere;
import pi.enset.repository.ClasseRepository;
import pi.enset.repository.FiliereRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IClasseServiceImpl implements IClasseService {
    private ClasseRepository classeRepository;
    private FiliereRepository filiereRepository;

    @Override
    public List<Classe> getClasses() {
        return classeRepository.findAll();
    }

    @Override
    public Classe addClasse(Classe classe, Long idFielre) {
        Filiere filiere = filiereRepository.findById(idFielre).orElseThrow(() -> new RuntimeException("La filiere avec id=" + idFielre + " n'existe pas!"));
        classe.setFiliere(filiere);
        return classeRepository.save(classe);
    }



    @Override
    public String deleteClasse(Long id) {
        try {
            getClasseById(id);
            classeRepository.deleteById(id);
            return "La suppression de classe est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Classe getClasseById(Long id) {
        return classeRepository.findById(id).orElseThrow(() -> new RuntimeException("La classe n'existe pas!"));
    }

    @Override
    public Classe updateClasse(Long id, Classe classe) {
        classe.setId(id);
       /* Long filiereId=classe.getFiliere().getId();
        Filiere filiere= filiereRepository.findById(filiereId).orElseThrow(() -> new RuntimeException("La filiere avec id="+filiereId+" n'existe pas!"));
        classe.setFiliere(filiere);*/
        System.out.println("........id filiere updateClasse .......");
        System.out.println(classe.getFiliere().getId());
        return classeRepository.save(classe);
    }

    @Override
    public Page<Classe> getClasses(Pageable pageable) {
        return classeRepository.findAll(pageable);
    }

    @Override
    public Page<Classe> searchClasses(String keyword, Long sem,Pageable pageable) {
        return classeRepository.searchClasses(keyword, sem, pageable);
    }
    @Override
    public Page<Classe> searchClasses(String keyword,Pageable pageable) {
        return classeRepository.searchClasses(keyword, pageable);
    }

}
