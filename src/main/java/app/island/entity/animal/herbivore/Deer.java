package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\deer.yaml")
public class Deer extends Herbivore {
    public Deer() {
        this.setName("Deer");
        this.setIcon("\uD83E\uDD8C");
        this.setWeightInKilograms(300d);
        this.setMaxAnimalsPerCell(20);
        this.setMaxMovementSpeedPerTurn(4);
        this.setFoodRequiredForFullSatiation(50d);

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
