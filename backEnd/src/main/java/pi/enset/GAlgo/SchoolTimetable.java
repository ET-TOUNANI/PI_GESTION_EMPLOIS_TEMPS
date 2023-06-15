package pi.enset.GAlgo;

import pi.enset.entities.Classe;
import pi.enset.entities.ElementDeModule;
import pi.enset.entities.Enseignant;
import pi.enset.entities.Module;

import java.util.ArrayList;
import java.util.List;

public class SchoolTimetable {
    private List<List<ElementDeModule>> schoolTimetables;
    private int fitness;

    public SchoolTimetable(int numberOfClasses) {
        this.schoolTimetables = new ArrayList<>(numberOfClasses);
        for (int i = 0; i < numberOfClasses; i++) {
            this.schoolTimetables.add(new ArrayList<>());
        }
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public List<Enseignant> getEnseignants() {
        List<Enseignant> enseignants = new ArrayList<>();
        for (List<ElementDeModule> timetable : schoolTimetables) {
            for (ElementDeModule element : timetable) {
                Enseignant enseignant = element.getEnseignant();
                if (enseignant != null && !enseignants.contains(enseignant)) {
                    enseignants.add(enseignant);
                }
            }
        }
        return enseignants;
    }

    public void addElementDeModule(int classIndex, ElementDeModule elementDeModule) {
        List<ElementDeModule> timetable = schoolTimetables.get(classIndex);
        timetable.add(elementDeModule);
    }

   /* public ElementDeModule getElementDeModule(int classIndex, int timetableIndex) {
        List<ElementDeModule> timetable = schoolTimetables.get(classIndex);
        return timetable.get(timetableIndex);
    }*/


    public List<ElementDeModule> getTimetable(int classIndex) {
        return schoolTimetables.get(classIndex);
    }

    public int getNumberOfClasses() {
        return schoolTimetables.size();
    }

    public int calculateFitness() {
        // calculate the fitness of the timetable by calculating the number of unsatisfied criteria

        Criterias criterias = new Criterias(this);
        int unsatisfiedCriteria = 0;

        // Criterion: La génération de l’emploi du temps doit prendre en compte les disponibilités des enseignants
        unsatisfiedCriteria += criterias.isDisponibilitesEnseignantsSatisfied();

        // Criterion: Un enseignant ne doit pas avoir plusieurs classes en même temps
        unsatisfiedCriteria += criterias.isEnseignantClasseConflictSatisfied();

        // Criterion: Une classe ne doit avoir deux séances/module au même horaire
        unsatisfiedCriteria += criterias.isClasseSeancesConflictSatisfied();

        // Criterion: Une salle doit être occupée par une seule classe à la fois
        unsatisfiedCriteria += criterias.isSalleOccupancySatisfied();

        // Criterion: Chaque classe doit avoir au moins une après-midi de libre (mercredi)
        unsatisfiedCriteria += criterias.isClasseAfternoonFreeDaySatisfied();

        this.fitness =  unsatisfiedCriteria;
        return this.fitness;
    }

    public void swapGenes(int classIndex, int position1, int position2) {
        List<ElementDeModule> timetable = schoolTimetables.get(classIndex);
        ElementDeModule gene1 = timetable.get(position1);
        ElementDeModule gene2 = timetable.get(position2);
        timetable.set(position1, gene2);
        timetable.set(position2, gene1);
    }

    public void setTimetable(int classIndex, List<ElementDeModule> classTimetable2) {
        schoolTimetables.set(classIndex, classTimetable2);
    }

    public List<Classe> getClasses() {
        List<Classe> classes = new ArrayList<>();
        for (List<ElementDeModule> timetable : schoolTimetables) {
            for (ElementDeModule element : timetable) {
                Classe classe = element.getModule().getClasse();
                if (classe != null && !classes.contains(classe)) {
                    classes.add(classe);
                }
            }
        }
        return classes;
    }

    public List<Module> getModules() {
        List<Module> modules = new ArrayList<>();
        for (List<ElementDeModule> timetable : schoolTimetables) {
            for (ElementDeModule element : timetable) {
                Module module = element.getModule();
                if (module != null && !modules.contains(module)) {
                    modules.add(module);
                }
            }
        }
        return modules;
    }

    public List<ElementDeModule> getAllElements() {
        List<ElementDeModule> elements = new ArrayList<>();
        for (List<ElementDeModule> timetable : schoolTimetables) {
            for (ElementDeModule element : timetable) {
                if (!elements.contains(element)) {
                    elements.add(element);
                }
            }
        }
        return elements;
    }
}
