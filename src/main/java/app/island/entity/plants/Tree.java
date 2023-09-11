package app.island.entity.plants;

import app.island.annotations.Config;

@Config(fileName = "config\\entities\\plants\\grass.yaml")
public class Tree extends Plants {
    public Tree() {
        this.setName("Tree");
        this.setIcon("\uD83C\uDF33");
        this.setWeightInKilograms(1d);
        this.setMaxPlantsPerCell(200);
    }
}
