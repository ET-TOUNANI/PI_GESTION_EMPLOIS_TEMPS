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

    public int isDisponibilitesEnseignantsSatisfied() {
        int couner = 0;
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
                    couner++;
                }
            }
        }
        return couner;
    }


    public int isEnseignantClasseConflictSatisfied() {
        int couner = 0;
        List<Enseignant> enseignants = schoolTimetable.getEnseignants();
        for (Enseignant enseignant : enseignants) {
            List<ElementDeModule> elements = (List<ElementDeModule>) enseignant.getElementDeModules();
            if (elements != null) {
                for (ElementDeModule element : elements) {
                    // Check if the enseignant has two elements in the jour and periode
                    boolean isSatisfied = elements.stream()
                            .noneMatch(elementCompar ->
                                    element.getJour() == elementCompar.getJour() &&
                                            element.getModule().getClasse().getSemestre().getNum().ordinal()%2==elementCompar.getModule().getClasse().getSemestre().getNum().ordinal()%2 &&
                                            elementCompar.getPeriode() == element.getPeriode() &&
                                            elementCompar.getId() != element.getId()/* &&
                                            elementCompar.getModule().isMetuale()==false &&
                                            elementCompar.getModule().getClasse() != element.getModule().getClasse()&&
                                            elementCompar.getModule().getClasse().getFiliere()== element.getModule().getClasse().getFiliere()
                           */ );
                    if (!isSatisfied) {
                        couner++;
                    }
                }
            }
        }
        return couner;
    }


    public int isClasseSeancesConflictSatisfied() {
        int couner = 0;
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
                                                        elementCompar.getPeriode() == element.getPeriode() &&
                                                        elementCompar.getId() != element.getId() &&
                                                        element.getModule().getClasse().getSemestre().getNum().ordinal()%2==elementCompar.getModule().getClasse().getSemestre().getNum().ordinal()%2/*&&
                                                        elementCompar.getModule().isMetuale()==false &&
                                                        elementCompar.getModule().getClasse() != element.getModule().getClasse()&&
                                                        elementCompar.getModule().getClasse().getFiliere()== element.getModule().getClasse().getFiliere()
*/
                                        );
                                if (!isSatisfied) {
                                    couner++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return couner;
    }

    public int isSalleOccupancySatisfied() {
        int couner = 0;
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
                                                && element.getId() != sc.getId() &&
                                                sc.getModule().isMetuale()==false &&
                                                element.getModule().getClasse().getSemestre().getNum().ordinal()%2==sc.getModule().getClasse().getSemestre().getNum().ordinal()%2 &&
                                                sc.getModule().getClasse().getFiliere().getDepartement()== element.getModule().getClasse().getFiliere().getDepartement()

                                );
                        if (isOccupied) {
                            couner++;
                        }
                    }
                }
            }
        }
        return couner;
    }

    public int isClasseAfternoonFreeDaySatisfied() {
        int couner = 0;
        List<Classe> classes = schoolTimetable.getClasses();
        for (Classe classe : classes) {
            List<Module> modules = (List<Module>) classe.getModules();
            if (modules != null) {
                boolean hasAfternoonFreeDay = modules.stream()
                        .flatMap(mod -> mod.getElementDeModules().stream())
                        .noneMatch(el -> el.getPeriode().equals(Periode.P3) && el.getPeriode().equals(Periode.P4) && el.getJour() == DayOfWeek.WEDNESDAY);

                if (!hasAfternoonFreeDay) {
                    couner++;
                }
            }
        }
        return couner;
    }

    public int areElementsAdjacent() {
        int couner = 0;
        List<Module> modules = schoolTimetable.getModules();
        for (Module module : modules) {
            if (!module.isSeperated()) {
                List<ElementDeModule> elements = (List<ElementDeModule>) module.getElementDeModules();
                if (elements != null) {
                    ElementDeModule currentElement = elements.get(0);
                    ElementDeModule nextElement = elements.get(1);
                    // Check if the elements are not adjacent in the timetable
                    if ((currentElement.getJour() != nextElement.getJour() &&
                    currentElement.getPeriode().ordinal() != nextElement.getPeriode().ordinal() - 1)|| (currentElement.getJour() != nextElement.getJour() &&
                            currentElement.getPeriode().ordinal() != nextElement.getPeriode().ordinal() + 1)) {

                        couner++;

                    }
                }
            }
        }
        return couner;
    }

    public int areElementsInSamePeriod() {
        int couner = 0;
        List<Classe> classes = schoolTimetable.getClasses();
        for (Classe classe : classes) {
            List<Module> modules = (List<Module>) classe.getModules();
            if (modules != null) {
                for (Module module : modules) {
                    if (module.isMetuale()) {
                        List<ElementDeModule> elements = (List<ElementDeModule>) module.getElementDeModules();
                        if (elements != null) {
                            for (ElementDeModule element : elements) {
                                // Check if there is an element in another class within the same department
                                // with the same module name that is in the same period
                                for (Classe otherClasse : classes) {
                                    if (otherClasse != classe && otherClasse.getFiliere().equals(classe.getFiliere())) {
                                        List<Module> otherModules = (List<Module>) otherClasse.getModules();
                                        if (otherModules != null) {
                                            for (Module otherModule : otherModules) {
                                                if (otherModule.getLibelle().equals(module.getLibelle())) {
                                                    List<ElementDeModule> otherElements = (List<ElementDeModule>) otherModule.getElementDeModules();
                                                    if (otherElements != null) {
                                                        boolean isSatisfied = otherElements.stream()
                                                                .noneMatch(otherElement ->
                                                                        otherElement.getPeriode() == element.getPeriode()
                                                                );
                                                        if (!isSatisfied) {
                                                            couner++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return couner;
    }

}
