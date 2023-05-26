package pi.enset.GAlgo;

import pi.enset.entities.Module;
import pi.enset.entities.*;
import pi.enset.entities.enums.Periode;

import java.time.DayOfWeek;
import java.util.List;

public class Criterias {
    SchoolTimetable schoolTimetable;

    public Criterias(SchoolTimetable schoolTimetable) {
        this.schoolTimetable = schoolTimetable;
    }

    public boolean isDisponibilitesEnseignantsSatisfied() {
        List<Enseignant> enseignants = schoolTimetable.getEnseignants();
        for (Enseignant enseignant : enseignants) {
            for (ElementDeModule element : enseignant.getElementDeModules()) {
                // Check if the enseignant is available for the seance's jour and periode
                boolean isAvailable = enseignant.getNonDisponibilites().stream()
                        .noneMatch(nonDispo ->
                                nonDispo.getJour() == element.getJour() &&
                                        nonDispo.getPeriode() == element.getPeriode()
                        );
                if (!isAvailable) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isEnseignantClasseConflictSatisfied() {
        List<Enseignant> enseignants = schoolTimetable.getEnseignants();
        for (Enseignant enseignant : enseignants) {
            List<ElementDeModule> elements = (List<ElementDeModule>) enseignant.getElementDeModules();
            if (elements != null) {
                for (ElementDeModule element : elements) {
                    // Check if the enseignant has two elements in the jour and periode
                    boolean isSatisfied = elements.stream()
                            .noneMatch(elementCompar ->
                                    element.getJour() == elementCompar.getJour() &&
                                            elementCompar.getPeriode() == element.getPeriode() && elementCompar.getId() != element.getId()
                            );
                    if (!isSatisfied) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public boolean isClasseSeancesConflictSatisfied() {
        List<Classe> classes = schoolTimetable.getClasses();
        for (Classe classe : classes) {
            List<Module> modules = (List<Module>) classe.getModules();
            if (modules != null) {
                for (Module module : modules) {
                    List<ElementDeModule> elements = (List<ElementDeModule>) module.getElementDeModules();
                    if (elements != null) {
                        for (ElementDeModule element : elements) {
                            // Check if the classe has two elements in the same jour and periode
                            for (Module module1 : modules) {
                                List<ElementDeModule> elements1 = (List<ElementDeModule>) module1.getElementDeModules();
                                boolean isSatisfied = elements1.stream()
                                        .noneMatch(elementCompar ->
                                                element.getJour() == elementCompar.getJour() &&
                                                        elementCompar.getPeriode() == element.getPeriode() && elementCompar.getId() != element.getId()
                                        );
                                if (!isSatisfied) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isSalleOccupancySatisfied() {
        List<Module> modules = schoolTimetable.getModules();
        for (Module module : modules) {
            List<ElementDeModule> elements = (List<ElementDeModule>) module.getElementDeModules();
            if (elements != null) {
                for (ElementDeModule element : elements) {
                    Salle salle = element.getSalle();
                    if (salle != null && salle.getElementDeModules() != null) {
                        // Check if the salle is already occupied for the element's jour and periode
                        boolean isOccupied = salle.getElementDeModules().stream()
                                .anyMatch(sc ->
                                        sc.getJour() == element.getJour() &&
                                                sc.getPeriode() == element.getPeriode()
                                                && element.getId() != sc.getId()
                                );
                        if (isOccupied) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isClasseAfternoonFreeDaySatisfied() {
        List<Classe> classes = schoolTimetable.getClasses();
        for (Classe classe : classes) {
            List<Module> modules = (List<Module>) classe.getModules();
            if (modules != null) {
                boolean hasAfternoonFreeDay = modules.stream()
                        .flatMap(mod -> mod.getElementDeModules().stream())
                        .noneMatch(el -> el.getPeriode().equals(Periode.P3) && el.getPeriode().equals(Periode.P4) && el.getJour() == DayOfWeek.WEDNESDAY);

                if (!hasAfternoonFreeDay) {
                    return false;
                }
            }
        }
        return true;
    }

/*
    public boolean isSurchargeEnseignantSatisfied() {
        List<Enseignant> enseignants = schoolTimetable.getEnseignants();
        for (Enseignant enseignant : enseignants) {
            List<ElementDeModule> elements = (List<ElementDeModule>) enseignant.getElementDeModules();
            if (elements != null) {
                boolean isSatisfied = elements.stream()
                        .noneMatch(element ->
                                element.getJour()
                        );

                if (!isSatisfied) {
                    return false;
                }
            }
        }
        // Add your implementation here
        return true;
    }

 */
}
