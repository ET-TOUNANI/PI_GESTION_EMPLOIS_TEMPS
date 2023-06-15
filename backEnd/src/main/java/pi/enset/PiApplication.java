package pi.enset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pi.enset.entities.Module;
import pi.enset.entities.*;
import pi.enset.entities.enums.NumeroSemester;
import pi.enset.entities.enums.Periode;
import pi.enset.entities.enums.TypeAdmin;
import pi.enset.entities.enums.TypeSalle;
import pi.enset.repository.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class PiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
    }

    @Bean
    CommandLineRunner lineRunner(UserRepository userRepository,
                                 DepartementRepostitory departementRepostitory,
                                 ClasseRepository classeRepository,
                                 ElementModuleRepository elementModuleRepository,
                                 SemestreRepository semestreRepository,
                                 SalleRepository salleRepository,
                                 FiliereRepository filiereRepository, ModuleRepository moduleRepository,
                                 NonDisponibiliteRepository nonDisponibiliteRepository) {
        return args -> {
            // Create the Admin
            Admin admin = new Admin();
            admin.setNom("Daiif");
            admin.setPrenom("Aziz");
            admin.setEmail("admin@admin.com");
            admin.setPassword("admin");
            admin.setTel("0600000000");
            admin.setCivilite("M");
            admin.setLogin("admin");
            admin.setCne("555");
            admin.setAdmin_type(TypeAdmin.SUPER_ADMIN);
            userRepository.save(admin);
            /*
            // Create the Departement
            Departement departement1 = new Departement();
            departement1.setLibelle("Math Info");
            departement1.setChefDepartement("Aziz Daiif");
            departementRepostitory.save(departement1);
            // Create the Departement
            Departement departement2 = new Departement();
            departement2.setLibelle("Mecanic");
            departement2.setChefDepartement("Hayani Yassin");
            departementRepostitory.save(departement2);
            // Create the Filiere with the Departement
            Filiere filiere = new Filiere();
            filiere.setLibelle("IIBDCC");
            filiere.setNombreSem(6);
            filiere.setChefFiliere("Azedine elkhiyat");
            filiere.setDepartement(departement1);
            filiereRepository.save(filiere);
            // Create the Filiere with the Departement
            Filiere filiere2 = new Filiere();
            filiere2.setLibelle("GIL");
            filiere2.setNombreSem(6);
            filiere2.setChefFiliere("RIyahi rachid");
            filiere2.setDepartement(departement2);
            filiereRepository.save(filiere2);
            // Create the Semestre
            Semestre semestre = new Semestre();
            semestre.setDateDebut(new Date());
            semestre.setDateFin(new Date());
            semestre.setNum(NumeroSemester.S1);
            semestre.setAnneeUniv("2023-2024");
            semestreRepository.save(semestre);

            // Create the Classe with the Filiere
            Classe classe = new Classe();
            classe.setLibelle("IIBDCC 1");
            classe.setNbrEleves(40);
            classe.setFiliere(filiere);
            classe.setSemestre(semestre);
            classeRepository.save(classe);
            // Create the Classe with the Filiere
            Classe classe2 = new Classe();
            classe2.setLibelle("GIL 1");
            classe2.setNbrEleves(40);
            classe2.setFiliere(filiere2);
            classe2.setSemestre(semestre);
            classeRepository.save(classe2);
            List<Classe> classes = new ArrayList<>();
            classes.add(classe);
            classes.add(classe2);


            //**************** for IIBDCC 1 ****************


            List<Module> modules = new ArrayList<>();
            Stream.of("Mathématiques appliquées 1", "Mathématiques appliquées 2", "Techniques de programmation"
                            , "Bases de données", "Technologie des ordinateurs et réseaux", "Economie et environnement de l’entreprise", "Technique d’expression et de communication 1"
                    )
                    .forEach(libelle -> {
                        // Create the Module
                        Module module = new Module();
                        module.setLibelle(libelle);
                        module.setVolumeHoraire(50);
                        module.setSeperated(true);
                        module.setMetuale(false);
                        module.setClasse(classe);
                        moduleRepository.save(module);
                        modules.add(module);
                    });
            List<Enseignant> enseignants = new ArrayList<>();
            Stream.of("Tadlawi", "Akif", "illosammen", "Mestari", "kbado", "Benmoussa", "Rebani", "Gemaat", "Elfalhi", "elkabssi", "youssfi", "safrawi", "Sellami", "Bouzidi", "Charfawat", "Berrada")
                    .forEach(o -> {
                        // Create the Enseignant
                        Enseignant enseignant = new Enseignant();
                        enseignant.setCivilite("Mr");
                        enseignant.setNom(o);
                        enseignant.setPrenom(o);
                        enseignant.setTel("08282993");
                        enseignant.setCne("1234567890");
                        enseignant.setEmail(o + "@example.com");
                        enseignant.setLogin(o);
                        enseignant.setPassword("password");
                        enseignant.setSpecialite("Java Programming");
                        userRepository.save(enseignant);
                        enseignants.add(enseignant);
                    });
            int[] moduleIndices = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
            int[] enseignantIndices = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            Stream.of(1, 23, 45, 67, 89, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20).forEach(o -> {
                // Create the Salle
                Salle salle = new Salle();
                salle.setNumSalle(o);
                salle.setCapacite(84);
                salle.setTypeSalle(TypeSalle.miniAmphi);
                salleRepository.save(salle);
            });

            String[] libelles = {"Analyse Numérique 1", "Logique et Algèbre Linéaire", "Probabilité", "Recherche opérationnelle", "Algorithmique", "Programmation en langage C", "Introduction aux bases de données", "SQL et SGBD", "Techniques de base pour les réseaux", "Architecture des ordinateurs et assembleur", "Economie générale", "Environnement socio-économique et institutionnel", "Technique d’expression et de communication 2", "Anglais"};
            // Iterate over the indices to create and save each element
            for (int i = 0; i < moduleIndices.length; i++) {
                // Create a new ElementDeModule
                ElementDeModule elementDeModule = new ElementDeModule();

                // Set the properties of the ElementDeModule
                elementDeModule.setLibelle(libelles[i]);
                elementDeModule.setVolumeHoraire(32);
                elementDeModule.setModule(modules.get(moduleIndices[i]));
                elementDeModule.setEnseignant(enseignants.get(enseignantIndices[i]));


                // Save the ElementDeModule
                elementModuleRepository.save(elementDeModule);
            }

            // Create the NonDisponibilite with the Enseignant
            NonDisponibilite nonDisponibilite = new NonDisponibilite();
            nonDisponibilite.setJour(DayOfWeek.MONDAY);
            nonDisponibilite.setPeriode(Periode.P1);
            nonDisponibilite.setEnseignant(enseignants.get(0));
            nonDisponibiliteRepository.save(nonDisponibilite);
            // Create the NonDisponibilite with the Enseignant
            NonDisponibilite nonDisponibilite2 = new NonDisponibilite();
            nonDisponibilite2.setJour(DayOfWeek.THURSDAY);
            nonDisponibilite2.setPeriode(Periode.P3);
            nonDisponibilite2.setEnseignant(enseignants.get(4));
            nonDisponibiliteRepository.save(nonDisponibilite2);


            //**************** for Gil 1 ****************

            List<Module> modules2 = new ArrayList<>();
            Stream.of("Mathématiques appliquées 1", "Mathématiques appliquées 2", "Techniques de programmation"
                            , "Bases de données", "Technologie des ordinateurs et réseaux", "Sciences de L'ingénieur 1", "Electronique et Automatique", "Technique d’expression et de communication 1 et Anglais", "Economie et environnement de l’entreprise"
                    )
                    .forEach(libelle -> {
                        // Create the Module
                        Module module = new Module();
                        module.setLibelle(libelle);
                        module.setVolumeHoraire(50);
                        module.setSeperated(true);
                        module.setMetuale(false);
                        module.setClasse(classe2);
                        moduleRepository.save(module);
                        modules2.add(module);
                    });
            List<Enseignant> enseignants2 = new ArrayList<>();
            Stream.of("moufid 1", "Rafik", "awatif", "beniis", "nisabori", "jemoukh", "nassif")
                    .forEach(o -> {
                        // Create the Enseignant
                        Enseignant enseignant = new Enseignant();
                        enseignant.setCivilite("Mr");
                        enseignant.setNom(o);
                        enseignant.setPrenom(o);
                        enseignant.setTel("092399");
                        enseignant.setCne("299293");
                        enseignant.setEmail(o + "@example.com");
                        enseignant.setLogin(o);
                        enseignant.setPassword("password");
                        enseignant.setSpecialite("Gil Programming");
                        userRepository.save(enseignant);
                        enseignants2.add(enseignant);
                    });
            enseignants2.add(enseignants.get(4));
            enseignants2.add(enseignants.get(9));
            enseignants2.add(enseignants.get(10));
            enseignants2.add(enseignants.get(8));
            enseignants2.add(enseignants.get(7));
            enseignants2.add(enseignants.get(5));
            enseignants2.add(enseignants.get(2));
            enseignants2.add(enseignants.get(6));
            int[] moduleIndices2 = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
            int[] enseignantIndices2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
            String[] libelles2 = {"Analyse Numérique 1", "Logique et Algèbre Linéaire", "Automatismes", "Construction mécanique", "Automatique linéaire", "Electronique analogique", "Economie générale", "Environnement socio-économique et institutionnel", "Construction mécanique alph", "Technique d’expression et de communication 2", "Anglais", "rwayda 22", "rwayda23", "rwayda24", "rwayda25"};
            // Iterate over the indices to create and save each element
            for (int i = 0; i < moduleIndices.length; i++) {
                // Create a new ElementDeModule
                ElementDeModule elementDeModule = new ElementDeModule();
                // Set the properties of the ElementDeModule
                elementDeModule.setLibelle(libelles2[i]);
                elementDeModule.setVolumeHoraire(32);
                elementDeModule.setModule(modules2.get(moduleIndices2[i]));
                elementDeModule.setEnseignant(enseignants2.get(enseignantIndices2[i]));
                // Save the ElementDeModule
                elementModuleRepository.save(elementDeModule);
            }

            // Create the NonDisponibilite with the Enseignant
            NonDisponibilite nonDisponibilite3 = new NonDisponibilite();
            nonDisponibilite3.setJour(DayOfWeek.MONDAY);
            nonDisponibilite3.setPeriode(Periode.P1);
            nonDisponibilite3.setEnseignant(enseignants2.get(0));
            nonDisponibiliteRepository.save(nonDisponibilite3);
            // Create the NonDisponibilite with the Enseignant
            NonDisponibilite nonDisponibilite4 = new NonDisponibilite();
            nonDisponibilite4.setJour(DayOfWeek.THURSDAY);
            nonDisponibilite4.setPeriode(Periode.P3);
            nonDisponibilite4.setEnseignant(enseignants2.get(4));
            nonDisponibiliteRepository.save(nonDisponibilite4);*/

        };

    }


}
