package pi.enset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pi.enset.entities.Departement;
import pi.enset.entities.Enseignant;
import pi.enset.repository.DepartementRepostitory;
import pi.enset.repository.EnseignantRepository;

@SpringBootApplication
public class PiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
    }

    @Bean
    CommandLineRunner lineRunner(EnseignantRepository userRepository, DepartementRepostitory departementRepostitory) {
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
        };
    }

}
