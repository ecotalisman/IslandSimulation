package app.island.entity.plants;

import app.island.Menu.Cell;
import app.island.Menu.Menu;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static app.island.Constants.Constants.*;
import static app.island.Menu.Menu.field;

public abstract class Plants extends Organism implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String icon;
    private double weightInKilograms;
    private int maxPlantsPerCell;
    private boolean isAlive = true;
    private final Object lock = new Object();
    public Plants(int maxPlantsPerCell) {
        this.maxPlantsPerCell = maxPlantsPerCell;
    }

    public synchronized void reproduce() {
        synchronized (lock) {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    Cell cell = field[i][j];
                    List<Organism> organismInCell = cell.getCell();
                    int entityCount = 0;
                    synchronized (organismInCell) {
                        for (Organism organism : organismInCell) {
                            if (organism.getClass().equals(this.getClass()) && ((Plants) organism).isAlive() ) {
                                entityCount++;
                            }
                        }
                    }
                    if (entityCount < getMaxPlantsPerCell()) {
                        List<Organism> newPlants = new ArrayList<>(organismInCell);
                        newPlants.add(createNewPlants());

                        synchronized (Menu.field) {
                            Menu.field[i][j].setCell(newPlants);
                        }
                    }
                }
            }
        }
    }

    protected abstract Plants createNewPlants();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getWeightInKilograms() {
        return weightInKilograms;
    }

    public void setWeightInKilograms(double weightInKilograms) {
        this.weightInKilograms = weightInKilograms;
    }

    public int getMaxPlantsPerCell() {
        return maxPlantsPerCell;
    }

    public void setMaxPlantsPerCell(int maxPlantsPerCell) {
        this.maxPlantsPerCell = maxPlantsPerCell;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plants plants = (Plants) o;

        if (Double.compare(weightInKilograms, plants.weightInKilograms) != 0) return false;
        if (maxPlantsPerCell != plants.maxPlantsPerCell) return false;
        if (isAlive != plants.isAlive) return false;
        if (!name.equals(plants.name)) return false;
        if (!icon.equals(plants.icon)) return false;
        return lock.equals(plants.lock);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + icon.hashCode();
        temp = Double.doubleToLongBits(weightInKilograms);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + maxPlantsPerCell;
        result = 31 * result + (isAlive ? 1 : 0);
        result = 31 * result + lock.hashCode();
        return result;
    }
}
