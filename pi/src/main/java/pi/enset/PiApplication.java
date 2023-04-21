package pi.enset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pi.enset.entities.*;
import pi.enset.entities.enums.NumeroSemester;
import pi.enset.repository.*;
import pi.enset.entities.Departement;
import pi.enset.entities.Enseignant;
import pi.enset.entities.Semestre;
import pi.enset.entities.TypeSalle;
import pi.enset.entities.enums.NumeroSemester;
import pi.enset.repository.DepartementRepostitory;
import pi.enset.repository.EnseignantRepository;
import pi.enset.repository.SemestreRepository;
import pi.enset.repository.TypeSalleRepository;

import java.util.Date;

@SpringBootApplication
public class PiApplication {
    private SemestreRepository semestreRepository;
    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
    }

    @Bean
    CommandLineRunner lineRunner(EnseignantRepository userRepository,
                                 DepartementRepostitory departementRepostitory,
                                 ClasseRepository classeRepository,
                                 ElementModuleRepository elementModuleRepository,
                                 SemestreRepository semestreRepository,
                                 TypeSalleRepository typeSalleRepository) {
        return args -> {
            // add some data to test
            Enseignant enseignant = new Enseignant();
            enseignant.setNom("ELYoussfi");
            enseignant.setPrenom("Mohammed");
            enseignant.setEmail("abo@gmail.com");
            enseignant.setCivilite("Mr");
            enseignant.setPassword("1111");
            enseignant.setTel("08282993");
            enseignant.setLogin("admin");
            enseignant.setSpecialite("Info Java");
            userRepository.save(enseignant);
            //*******************************
            Departement d =  Departement.builder()
                    .libelle("INFO")
                    .build();
            departementRepostitory.save(d);
            Departement d2 =  Departement.builder()
                    .libelle("mecinic")
                    .build();
            departementRepostitory.save(d2);
            Departement d3 =  Departement.builder()
                    .libelle("economie")
                    .build();
            departementRepostitory.save(d3);
            //*******************************
            Classe classe1 = Classe.builder()
                    .libelle("BDCC 2").
                    nbrEleves(42).build();
            classeRepository.save(classe1);
            Classe classe2 = Classe.builder()
                    .libelle("GLSID 2").
                    nbrEleves(39).build();
            classeRepository.save(classe2);
            Classe classe3 = Classe.builder()
                    .libelle("GIL 2").
                    nbrEleves(30).build();
            classeRepository.save(classe3);
            //*******************************
            ElementDeModule elementDeModule1 = new ElementDeModule();
            elementDeModule1.setLibelle("Architecture distribuée J2EE");
            elementDeModule1.setVolumeHoraire(32);
            elementModuleRepository.save(elementDeModule1);
            //**********************************************//
            ElementDeModule elementDeModule2 = new ElementDeModule();
            elementDeModule2.setLibelle("Middlewares");
            elementDeModule2.setVolumeHoraire(24);
            elementModuleRepository.save(elementDeModule2);
            //**********************************************//
            ElementDeModule elementDeModule3 = new ElementDeModule();
            elementDeModule3.setLibelle("Initiation à l'entreprenariat ");
            elementDeModule3.setVolumeHoraire(26);
            elementModuleRepository.save(elementDeModule3);
            //*******************************
            Semestre s1=Semestre.builder()
                    .anneeUniv("2022-2023")
                    .num(NumeroSemester.S1)
                    .dateDebut(new Date())
                    .dateFin(new Date())
                    .build();
            semestreRepository.save(s1);
            Semestre s2=Semestre.builder()
                    .anneeUniv("2022-2023")
                    .num(NumeroSemester.S2)
                    .dateDebut(new Date())
                    .dateFin(new Date())
                    .build();
            semestreRepository.save(s2);
            TypeSalle typeSalle1= TypeSalle.builder()
                    .capacite(12)
                    .libelle("tp")
                    .equipement(" reseaux , routeurs")
                    .build();
            //**********************************//
            typeSalleRepository.save(typeSalle1);
            TypeSalle typeSalle2= TypeSalle.builder()
                    .capacite(50)
                    .libelle("td")
                    .equipement(" reseaux , routeurs")
                    .build();
            typeSalleRepository.save(typeSalle2);

        };
    }

}
