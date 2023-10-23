package app.island.Menu;

import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.animal.herbivore.*;
import app.island.entity.animal.predator.*;
import app.island.entity.plants.Grass;
import app.island.entity.plants.Plants;
import app.island.entity.plants.Tree;
import app.island.exceptions.IslandException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static app.island.Constants.Constants.*;

public class IslandSimulation {
    private static Cell[][] field = new Cell[NUM_ROWS][NUM_COLUMNS];
    public static volatile Random random = new Random();
    public static Statistics statistics = new Statistics();
    private final static Object lock = new Object();
    private boolean allHerbivoreDead = false;

    public IslandSimulation() {
        initAllCells();
    }

    public void run() {
        while (true) {
            statisticsMap();
            boolean anyAlive = false;
            Map<Class<? extends Organism>, List<Organism>> groupedOrganisms = new HashMap<>();
            synchronized (statistics) {
                for (Map.Entry<Organism, Statistics.AnimalStatus> entry : statistics.getStatusMap().entrySet()) {
                    Organism organism = entry.getKey();
                    Statistics.AnimalStatus status = entry.getValue();

                    if (status.isAlive) {
                        anyAlive = true;
                        Class<? extends Organism> organismClass = organism.getClass();
                        List<Organism> organismList = groupedOrganisms.computeIfAbsent(organismClass, k -> new ArrayList<>());
                        organismList.add(organism);

                        synchronized (lock) {
                            if (organism instanceof Herbivore) {
                                allHerbivoreDead = false;
                            }
                        }
                    }
                }
            }
            ExecutorService executorService = Executors.newFixedThreadPool(groupedOrganisms.size());

            if (!anyAlive || allHerbivoreDead) {
                executorService.shutdownNow();
                break;
            }

            synchronized (lock) {
                for (Map.Entry<Class<? extends Organism>, List<Organism>> entry : groupedOrganisms.entrySet()) {
                    Class<? extends Organism> organismClass = entry.getKey();
                    List<Organism> organismList = entry.getValue();
                    Organism sampleOrganism = organismList.get(0);

                    if (sampleOrganism instanceof Animal && ((Animal) sampleOrganism).isAlive()) {
                        executorService.submit((Runnable) sampleOrganism);

                        System.out.println("Animal: " + ((Animal) sampleOrganism).getName() + " " + ((Animal) sampleOrganism).getIcon());
                        System.out.println("Is Alive: " + organismList.size());
                        System.out.println("Weight in Kilograms: " + ((Animal) sampleOrganism).getWeightInKilograms());
                        System.out.println("--------------------------");
                    } else if (sampleOrganism instanceof Plants && ((Plants) sampleOrganism).isAlive()) {
                        executorService.submit((Runnable) sampleOrganism);

                        System.out.println("Plants: " + ((Plants) sampleOrganism).getName() + ((Plants) sampleOrganism).getIcon());
                        System.out.println("Is Alive: " + organismList.size());
                        System.out.println("Weight in Kilograms: " + ((Plants) sampleOrganism).getWeightInKilograms());
                        System.out.println("--------------------------");
                    }
                }
            }
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void initAllCells() {
        Class<? extends Animal>[] animalClasses = new Class[]{
                Boar.class, Buffalo.class, Caterpillar.class, Deer.class, Duck.class,
                Goat.class, Horse.class, Mouse.class, Rabbit.class, Sheep.class,
                Bear.class, Eagle.class, Fox.class, Snake.class, Wolf.class
        };

        Class<? extends Plants>[] plantClasses = new Class[]{
                Grass.class, Tree.class
        };

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                field[i][j] = new Cell();
                try {
                    for (Class<? extends Animal> animalClass : animalClasses) {
                        int numAnimals = 0;
                        try {
                            Animal animal = animalClass.getDeclaredConstructor().newInstance();
                            numAnimals = IslandSimulation.random.nextInt(animal.getMaxAnimalsPerCell() + 1) / REDUCTION_FACTOR;
                        } catch (InstantiationException e) {
                            throw new IslandException("Failed to create an instance of Animal", e);
                        }
                        for (int k = 0; k < numAnimals; k++) {
                            try {
                                field[i][j].addOrganism(animalClass.getDeclaredConstructor().newInstance());
                            } catch (InstantiationException e) {
                                throw new IslandException("Failed to create an instance of Animal", e);
                            }
                        }
                    }

                    for (Class<? extends Plants> plantClass : plantClasses) {
                        int numPlants = 0;
                        try {
                            Plants plant = plantClass.getDeclaredConstructor().newInstance();
                            numPlants = IslandSimulation.random.nextInt(plant.getMaxPlantsPerCell() + 1) / REDUCTION_FACTOR_PLANTS;
                        } catch (InstantiationException e) {
                            throw new IslandException("Failed to create an instance of Plants", e);
                        }
                        for (int k = 0; k < numPlants; k++) {
                            try {
                                field[i][j].addOrganism(plantClass.getDeclaredConstructor().newInstance());
                            } catch (InstantiationException e) {
                                throw new IslandException("Failed to create an instance of Plants", e);
                            }
                        }
                    }
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new IslandException("Exception while creating instances", e);
                }
            }
        }
        statisticsMap();
    }

    private synchronized void statisticsMap() {
        boolean anyHerbivoreAlive = false;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                List<Organism> cell = field[i][j].getCell();
                List<Organism> copyCell = new ArrayList<>(cell);

                for (Organism currentOrganism : copyCell) {
                    if (currentOrganism == null) {
                        continue;
                    }
                    Statistics.AnimalStatus status = statistics.new AnimalStatus();
                    if (currentOrganism instanceof Animal) {
                        status.isAlive = ((Animal) currentOrganism).isAlive();
                        status.weightInKilograms = ((Animal) currentOrganism).getWeightInKilograms();
                    } else {
                        status.isAlive = ((Plants) currentOrganism).isAlive();
                        status.weightInKilograms = ((Plants) currentOrganism).getWeightInKilograms();
                    }
                    statistics.updateStatus(currentOrganism, status);
                    if (currentOrganism instanceof Herbivore && ((Herbivore) currentOrganism).isAlive()) {
                        anyHerbivoreAlive = true;
                    }
                }
            }
        }
        synchronized (lock) {
            allHerbivoreDead = !anyHerbivoreAlive;
        }
    }

    public static synchronized Cell getCellAt(int x, int y) {
        return field[x][y];
    }

    public static synchronized void setCellAt(int x, int y, Cell cell) {
        field[x][y] = cell;
    }
}