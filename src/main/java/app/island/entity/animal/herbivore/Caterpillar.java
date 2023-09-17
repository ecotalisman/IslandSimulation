package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\caterpillar.yaml")
public class Caterpillar extends Herbivore {
    public Caterpillar() {
        this.setName("Caterpillar");
        this.setIcon("\uD83D\uDC1B");
        this.setWeightInKilograms(0.01d);
        this.setMaxAnimalsPerCell(1000);
        this.setMaxMovementSpeedPerTurn(0);
        this.setFoodRequiredForFullSatiation(0);

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
