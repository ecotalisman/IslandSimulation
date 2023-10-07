package app.island.entity.plants;

import app.island.annotations.Config;
import app.island.exceptions.IslandException;

@Config(fileName = "config\\entities\\plants\\tree.yaml")
public class Tree extends Plants implements Runnable {
    public Tree() {
        super(100);
        this.setName("Tree");
        this.setIcon("\uD83C\uDF33");
        this.setWeightInKilograms(100d);
        this.setMaxPlantsPerCell(100);
    }
    @Override
    protected Plants createNewPlants() {
        return new Tree();
    }
    @Override
    public void run() {
        while (isAlive()) {
            reproduce();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IslandException("Error while waiting", e);
            }
        }
    }
}
