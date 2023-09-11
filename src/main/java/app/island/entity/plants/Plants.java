package app.island.entity.plants;

import app.island.entity.Organism;

import java.io.Serializable;

public abstract class Plants extends Organism implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String icon;
    private double weightInKilograms;
    private int maxPlantsPerCell;
    private boolean isAlive = true;

    public void reproduce() {

    }

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
    public String toString() {
        return "Plants{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", weightInKilograms=" + weightInKilograms +
                ", maxPlantsPerCell=" + maxPlantsPerCell +
                '}';
    }
}
