package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\goat.yaml")
public class Goat extends Herbivore {
    public Goat() {
        this.setName("Goat");
        this.setIcon("\uD83D\uDC10");
        this.setWeightInKilograms(60d);
        this.setMaxAnimalsPerCell(140);
        this.setMaxMovementSpeedPerTurn(3);
        this.setFoodRequiredForFullSatiation(10d);

        this.setPredationProbability(Map.of(
                Plants.class, 100));
    }
    @Override
    public void eat(Organism organism) {
        Integer value = getPredationProbability().getOrDefault(organism.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
