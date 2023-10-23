package app.island.entity.plants;

import app.island.Menu.Cell;
import app.island.Menu.IslandSimulation;
import app.island.entity.Organism;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static app.island.Constants.Constants.*;
import static app.island.Menu.IslandSimulation.random;

public abstract class Plants extends Organism implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String icon;
    private double weightInKilograms;
    private int maxPlantsPerCell;
    private boolean isAlive = true;
    private final Object lock = new Object();
    public Plants(double weightInKilograms, int maxPlantsPerCell) {
        setWeightInKilograms(weightInKilograms);
        setMaxPlantsPerCell(maxPlantsPerCell);
    }

    public void reproduce() {
        synchronized (lock) {
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    Cell cell = IslandSimulation.getCellAt(i, j);
                    List<Organism> organismInCell = new ArrayList<>(cell.getCell());
                    long entityCount = organismInCell.stream()
                            .filter(o -> o.getClass().equals(this.getClass()) && ((Plants) o).isAlive())
                            .count();

                    if (random.nextInt(100) < 10 && entityCount < getMaxPlantsPerCell()) {
                        organismInCell.add(createNewPlants());
                        cell.setCell(organismInCell);
                        IslandSimulation.setCellAt(i, j, cell);
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
        if (!Objects.equals(name, plants.name)) return false;
        if (!Objects.equals(icon, plants.icon)) return false;
        return lock.equals(plants.lock);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        temp = Double.doubleToLongBits(weightInKilograms);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + maxPlantsPerCell;
        result = 31 * result + (isAlive ? 1 : 0);
        result = 31 * result + lock.hashCode();
        return result;
    }
}
