package pi.enset.GAlgo;

import pi.enset.entities.Classe;
import pi.enset.entities.ElementDeModule;
import pi.enset.entities.Module;
import pi.enset.entities.enums.Periode;
import pi.enset.settings.DataFromDb;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GaAlgorithm {
    private  final int POPULATION_SIZE = 10;
    private  final double MUTATION_RATE = 0.2;
    private  final double CROSSOVER_RATE = 0.9;
    private  final int MAX_GENERATIONS = 50;
    Periode[] timeslots;
    List<DayOfWeek> days;

     private   final int targetFitness = 1;
    Random random = new Random();
    private List<SchoolTimetable> population;
    private boolean isTerminated;

    public GaAlgorithm() {
        this.population = new ArrayList<>(POPULATION_SIZE);
        this.isTerminated = false;

        timeslots = Periode.values();
        days = new ArrayList<>();
        days.add(DayOfWeek.MONDAY);
        days.add(DayOfWeek.TUESDAY);
        days.add(DayOfWeek.WEDNESDAY);
        days.add(DayOfWeek.THURSDAY);
        days.add(DayOfWeek.FRIDAY);
    }

    private DayOfWeek getRandomDay() {
        int index = random.nextInt(days.size());
        return days.get(index);
    }

    private Periode getRandomPeriode(DayOfWeek day) {
        // if day equal wednesday and periode equal P3 or P4 then rgenerate periode
        if (day.equals(DayOfWeek.WEDNESDAY)) {
            int index = random.nextInt(timeslots.length);
            while (timeslots[index].equals(Periode.P3) || timeslots[index].equals(Periode.P4)) {
                index = random.nextInt(timeslots.length);
            }
            return timeslots[index];
        }
        else {
            int index = random.nextInt(timeslots.length);
            return timeslots[index];
        }
    }

    private List<ElementDeModule> getElementsForClasse(Classe classe) {
        List<ElementDeModule> elementDeModules = new ArrayList<>();
        for (Module module : classe.getModules()) {
            elementDeModules.addAll(module.getElementDeModules());
        }
        return elementDeModules;
    }

    private int getRandomRoom(ElementDeModule elementDeModule) {
        int sumNbr = elementDeModule.getModule().getClasse().getNbrEleves();
        int index = 0;
        for (int i = 0; i < DataFromDb.rooms.size(); i++) {
            index = random.nextInt(DataFromDb.rooms.size());
            if (DataFromDb.rooms.get(index).getCapacite() >= sumNbr)
                return index;
        }
        return index;
    }
/*
    public void initializePopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            SchoolTimetable schoolTimetable = new SchoolTimetable(DataFromDb.classes.size());
            for (int classIndex = 0; classIndex < DataFromDb.classes.size(); classIndex++) {

                Classe classe = DataFromDb.classes.get(classIndex);
                List<ElementDeModule> elements = getElementsForClasse(classe);

                for (ElementDeModule element : elements) {
                    DayOfWeek day = getRandomDay();
                    Periode periode = getRandomPeriode(day);
                    int roomIndex = getRandomRoom(element);
                    element.setJour(day);
                    element.setPeriode(periode);
                    element.setSalle(DataFromDb.rooms.get(roomIndex));
                    schoolTimetable.addElementDeModule(classIndex, element);
                    // schoolTimetable.setElementDeModule(classIndex, schoolTimetable.getTimetable(classIndex).size()-1, element);
                }
            }
            population.add(schoolTimetable);
        }
    }
*/
public void initializePopulation() {
    for (int i = 0; i < POPULATION_SIZE; i++) {
        SchoolTimetable schoolTimetable = new SchoolTimetable(DataFromDb.classes.size());
        for (int classIndex = 0; classIndex < DataFromDb.classes.size(); classIndex++) {
            Classe classe = DataFromDb.classes.get(classIndex);
            List<ElementDeModule> elements = getElementsForClasse(classe);

            // Shuffle the elements to introduce randomness
            Collections.shuffle(elements);

            for (ElementDeModule element : elements) {
                DayOfWeek day = getRandomDay();
                Periode periode = getRandomPeriode(day);
                int roomIndex = getRandomRoom(element);
                element.setJour(day);
                element.setPeriode(periode);
                element.setSalle(DataFromDb.rooms.get(roomIndex));
                schoolTimetable.addElementDeModule(classIndex, element);
            }
        }
        population.add(schoolTimetable);
    }
}

    public void printTimetable(SchoolTimetable schoolTimetable) {
        System.out.println("SchoolTimetable with fitness : " + schoolTimetable.getFitness() + " %");
        for (int classIndex = 0; classIndex < schoolTimetable.getNumberOfClasses(); classIndex++) {
            System.out.println("Class " + schoolTimetable.getTimetable(classIndex).get(0).getModule().getClasse().getLibelle() + ":");
            List<ElementDeModule> classTimetable = schoolTimetable.getTimetable(classIndex);
            System.out.println("classTimetable size " + classTimetable.size());
            for (ElementDeModule element : classTimetable) {
                String day = element.getJour().toString();
                String period = element.getPeriode().toString();
                String room = element.getSalle().getTypeSalle() + " " + element.getSalle().getNumSalle();
                String module = element.getModule().getLibelle();

                System.out.println("Day: " + day + ", Period: " + period + ", Room: " + room + ", Module: " + module + ", Enseignant: " + element.getEnseignant().getNom() + " Element: " + element.getLibelle());
            }
            System.out.println();
        }
    }

    public void evolve() {
        for (int generation = 0; generation < MAX_GENERATIONS; generation++) {
            List<SchoolTimetable> newPopulation = new ArrayList<>(POPULATION_SIZE);

            for (int j = 0; j < POPULATION_SIZE / 2; j++) {
                SchoolTimetable parent1 = selectParent();
                SchoolTimetable parent2 = selectParent();

                if (random.nextDouble() <= CROSSOVER_RATE) {
                    List<SchoolTimetable> children = crossover(parent1, parent2);

                    if (random.nextDouble() <= MUTATION_RATE) {
                        mutate(children.get(0));
                    }
                    if (random.nextDouble() <= MUTATION_RATE) {
                        mutate(children.get(1));
                    }

                    newPopulation.add(children.get(0));
                    newPopulation.add(children.get(1));
                } else {
                    newPopulation.add(parent1);
                    newPopulation.add(parent2);
                }
            }

            // Calculate fitness for the new population
            for (SchoolTimetable schoolTimetable : newPopulation) {
                schoolTimetable.calculateFitness();
            }

            population = newPopulation;

            System.out.println("Generation " + generation + " with population size: " + population.size());
            for (SchoolTimetable schoolTimetable : population) {
                System.out.println(schoolTimetable.getFitness());
            }

            // Termination condition based on fitness threshold or lack of improvement
            if ( getBestTimetable().getFitness() <= targetFitness) {
                isTerminated = true;
                break;
            }
        }
    }

    public SchoolTimetable getBestTimetable() {
        // if (isTerminated) {
        SchoolTimetable bestSchoolTimetable = population.get(0);
        double bestFitness = bestSchoolTimetable.calculateFitness();

        for (int i = 1; i < POPULATION_SIZE; i++) {
            SchoolTimetable currentSchoolTimetable = population.get(i);
            double currentFitness = currentSchoolTimetable.calculateFitness();

            if (currentFitness < bestFitness) {
                bestSchoolTimetable = currentSchoolTimetable;
                bestFitness = currentFitness;
            }
        }

        return bestSchoolTimetable;
       /* } else {
            throw new IllegalStateException("Algorithm has not terminated yet.");
        }*/
    }

    private SchoolTimetable selectParent() {
        int totalFitness = 0;
        for (SchoolTimetable schoolTimetable : population) {
            totalFitness += schoolTimetable.calculateFitness();
        }

        int randomFitness = random.nextInt(totalFitness);
        int cumulativeFitness = 0;

        for (SchoolTimetable schoolTimetable : population) {
            cumulativeFitness += (totalFitness - schoolTimetable.calculateFitness());
            if (cumulativeFitness > randomFitness) {
                return schoolTimetable;
            }
        }

        // If no individual is selected (should not happen), return a random one
        return population.get(random.nextInt(population.size()));
    }

    public List<SchoolTimetable> crossover(SchoolTimetable parent1, SchoolTimetable parent2) {
        Random random = new Random();
        int numberOfClasses = parent1.getNumberOfClasses();
        // Create offspring timetables
        SchoolTimetable offspring1 = new SchoolTimetable(numberOfClasses);
        SchoolTimetable offspring2 = new SchoolTimetable(numberOfClasses);

        // Perform crossover for each class
        for (int classIndex = 0; classIndex < numberOfClasses; classIndex++) {
            List<ElementDeModule> parent1Timetable = parent1.getTimetable(classIndex);// IIBDCC emplois du temps 1
            List<ElementDeModule> parent2Timetable = parent2.getTimetable(classIndex);// IIBDCC emplois du temps 2

            int timetableSize = parent1Timetable.size();

            // Determine crossover point
            int crossoverPoint = random.nextInt((timetableSize / 2) - 1) + 1;
            //exit(0);
            // Create child timetables by combining parent schedules
            List<ElementDeModule> child1Timetable = new ArrayList<>(parent1Timetable.subList(0, crossoverPoint));
            List<ElementDeModule> child2Timetable = new ArrayList<>(parent2Timetable.subList(0, crossoverPoint));

            List<ElementDeModule> remainingElementsParent1 = parent1Timetable.stream().filter(element -> !child2Timetable.contains(element)).toList();

            List<ElementDeModule> remainingElementsParent2 = parent2Timetable.stream().filter(element -> !child1Timetable.contains(element)).toList();

            // Add remaining elements from the other parent to each child timetable
            child1Timetable.addAll(remainingElementsParent2);
            child2Timetable.addAll(remainingElementsParent1);

            // Add child timetables to offspring
            offspring1.getTimetable(classIndex).addAll(child1Timetable);
            offspring2.getTimetable(classIndex).addAll(child2Timetable);
        }

        List<SchoolTimetable> offspring = new ArrayList<>();
        offspring.add(offspring1);
        offspring.add(offspring2);

        return offspring;
    }

    private void mutate(SchoolTimetable schoolTimetable) {
        // Select a class to mutate
        int classIndex = random.nextInt(schoolTimetable.getNumberOfClasses());
        List<ElementDeModule> classTimetable = schoolTimetable.getTimetable(classIndex);

        // Select two positions in the class timetable
        int position1 = random.nextInt(classTimetable.size());
        int position2 = random.nextInt(classTimetable.size());
        // Randomly reassign day and period for the element in the updated timetable

        DayOfWeek randomDay = getRandomDay();
        Periode randomPeriod = getRandomPeriode(randomDay);
        classTimetable.get(position1).setJour(randomDay);
        classTimetable.get(position1).setPeriode(randomPeriod);
        // Swap the elements at the selected positions
        schoolTimetable.swapGenes(classIndex, position1, position2);
/*
        // Select another individual to get the new timetable from
        int classIndex2 = random.nextInt(schoolTimetable.getNumberOfClasses());
        List<ElementDeModule> classTimetable2 = schoolTimetable.getTimetable(classIndex2);

        // Replace the timetable of the selected class with the timetable from the second individual
        schoolTimetable.setTimetable(classIndex, classTimetable2);
*/

    }


    public SchoolTimetable generateTimetable() {
        initializePopulation();
        evolve();

        return getBestTimetable();
        //System.out.println("****************** individual best *******************");
        //printTimetable(bestSchoolTimetable);
    }

}
