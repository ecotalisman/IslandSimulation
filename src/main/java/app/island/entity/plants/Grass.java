package app.island.entity.plants;

import app.island.annotations.Config;
import app.island.exceptions.IslandException;

@Config(fileName = "config\\entities\\plants\\grass.yaml")
public class Grass extends Plants implements Runnable {
    public Grass() {
        super(1d, 200);
        this.setName("Grass");
        this.setIcon("\uD83C\uDF31");
        this.setWeightInKilograms(1d);
        this.setMaxPlantsPerCell(200);
    }
    @Override
    protected Plants createNewPlants() {
        return new Grass();
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
