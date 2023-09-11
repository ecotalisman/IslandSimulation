package app.island.entity.plants;

import app.island.annotations.Config;

@Config(fileName = "config\\entities\\plants\\grass.yaml")
public class Grass extends Plants {
    public Grass() {
        this.setName("Grass");
        this.setIcon("\uD83C\uDF31");
        this.setWeightInKilograms(1d);
        this.setMaxPlantsPerCell(200);
    }
}
