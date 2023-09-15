package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\caterpillar.yaml")
public class Caterpillar extends Herbivore {
    public Caterpillar() {
        this.setName("Caterpillar");
        this.setIcon("\uD83D\uDC1B");
        this.setWeightInKilograms(0.01d);
        this.setMaxAnimalsPerCell(1000);
        this.setMaxMovementSpeedPerTurn(3);
        this.setFoodRequiredForFullSatiation(100d);

        this.setPredationProbability(Map.of(
                Plants.class, 100));
    }
    @Override
    public void eat(Animal animal) {
        Integer value = getPredationProbability().getOrDefault(animal.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
