package pi.enset.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pi.enset.entities.Departement;
import pi.enset.entities.Filiere;
import pi.enset.repository.DepartementRepostitory;

import java.util.List;

@Service
@AllArgsConstructor
public class IDepartemenServiceImpl implements IDepartementService {
    private DepartementRepostitory departementRepostitory;

    @Override
    public List<Departement> getDepartements() {
        // in the moment we will not use pagination
        return departementRepostitory.findAll();
    }
    @Override
    public List<Departement> findDepartementByNom(String nom) {
        // in the moment we will not use pagination
        return  departementRepostitory.findDepartementByLibelle(nom);
    }

    @Override
    public Departement addDepartement(Departement departement) {
        return departementRepostitory.save(departement);
    }

    @Override
    public String deleteDepartement(Long id) {
        try {
            getDepartementById(id);
            departementRepostitory.deleteById(id);
            return "L'opération est bien effectuée";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Departement getDepartementById(Long id) {
        return departementRepostitory.findById(id).orElseThrow(() -> new RuntimeException("Le déparetement n'existe pas!"));
    }

    @Override
    public Departement updateDeparetement(Long id, Departement departement) {
        departement.setId(id);
        return departementRepostitory.save(departement);
    }

    @Override
    public Page<Departement> searchDepartements(String keyword, Pageable pageable) {
        return departementRepostitory.searchWithPagination(keyword, pageable);
    }

    @Override
    public List<Filiere> getFilieresByDepartmentId(Long id) {
        return departementRepostitory.getFilieresByDepartmentId(id);
    }
}
