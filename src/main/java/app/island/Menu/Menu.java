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
import java.util.List;
import java.util.Map;
import java.util.Random;

import static app.island.Constants.Constants.*;

public class Menu {
    public static Cell[][] field = new Cell[NUM_ROWS][NUM_COLUMNS];
    public static volatile Random random = new Random();
    private static Statistics statistics = new Statistics();

    public Menu() {
        initAllCells();
    }

    public void run() {
        for (Map.Entry<Organism, Statistics.AnimalStatus> entry : statistics.statusMap.entrySet()) {
            Organism organism = entry.getKey();
            Statistics.AnimalStatus status = entry.getValue();

            if (organism instanceof Animal) {
                System.out.println("Animal: " + ((Animal) organism).getName() + " " + ((Animal) organism).getIcon());
                System.out.println("Is Alive: " + status.isAlive);
                System.out.println("Weight in Kilograms: " + status.weightInKilograms);
                System.out.println("--------------------------");
            } else {
                System.out.println("Plants: " + ((Plants) organism).getName() + ((Plants) organism).getIcon());
                System.out.println("Is Alive: " + status.isAlive);
                System.out.println("Weight in Kilograms: " + status.weightInKilograms);
                System.out.println("--------------------------");
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
                            numAnimals = Menu.random.nextInt(animal.getMaxAnimalsPerCell() + 1);
                        } catch (InstantiationException e) {
                            throw new IslandException("Failed to create an instance of Animal", e);
                        }
                        for (int k = 0; k < numAnimals; k++) {
                            try {
                                field[i][j].addAnimal(animalClass.getDeclaredConstructor().newInstance());
                            } catch (InstantiationException e) {
                                throw new IslandException("Failed to create an instance of Animal", e);
                            }
                        }
                    }

                    for (Class<? extends Plants> plantClass : plantClasses) {
                        int numPlants = 0;
                        try {
                            Plants plant = plantClass.getDeclaredConstructor().newInstance();
                            numPlants = Menu.random.nextInt(plant.getMaxPlantsPerCell() + 1);
                        } catch (InstantiationException e) {
                            throw new IslandException("Failed to create an instance of Plants", e);
                        }
                        for (int k = 0; k < numPlants; k++) {
                            try {
                                field[i][j].addAnimal(plantClass.getDeclaredConstructor().newInstance());
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

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                List<Organism> cell = field[i][j].getCell();
                for (Organism organism : cell) {
                    Statistics.AnimalStatus status = statistics.new AnimalStatus();
                    if (organism instanceof Animal) {
                        status.isAlive = ((Animal) organism).isAlive();
                        status.weightInKilograms = ((Animal) organism).getWeightInKilograms();
                    } else {
                        status.isAlive = ((Plants) organism).isAlive();
                        status.weightInKilograms = ((Plants) organism).getWeightInKilograms();
                    }
                    statistics.statusMap.put(organism, status);
                }
            }
        }
    }
}
