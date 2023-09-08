package app.island.entity.plants;

import java.io.Serializable;

public abstract class Plants implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int weightInKilograms;
    private int maxPlantsPerCell;

    public void reproduce() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeightInKilograms() {
        return weightInKilograms;
    }

    public void setWeightInKilograms(int weightInKilograms) {
        this.weightInKilograms = weightInKilograms;
    }

    public int getMaxPlantsPerCell() {
        return maxPlantsPerCell;
    }

    public void setMaxPlantsPerCell(int maxPlantsPerCell) {
        this.maxPlantsPerCell = maxPlantsPerCell;
    }

    @Override
    public String toString() {
        return "Plants{" +
                "name='" + name + '\'' +
                ", weightInKilograms=" + weightInKilograms +
                ", maxPlantsPerCell=" + maxPlantsPerCell +
                '}';
    }
}
