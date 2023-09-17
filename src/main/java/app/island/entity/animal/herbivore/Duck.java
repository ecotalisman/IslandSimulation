package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\duck.yaml")
public class Duck extends Herbivore {
    public Duck() {
        this.setName("Duck");
        this.setIcon("\uD83E\uDD86");
        this.setWeightInKilograms(1d);
        this.setMaxAnimalsPerCell(200);
        this.setMaxMovementSpeedPerTurn(4);
        this.setFoodRequiredForFullSatiation(0.15d);

        this.setPredationProbability(Map.of(
                Caterpillar.class, 90,
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
