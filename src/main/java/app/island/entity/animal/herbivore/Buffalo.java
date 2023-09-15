package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\buffalo.yaml")
public class Buffalo extends Herbivore {
    public Buffalo() {
        this.setName("Buffalo");
        this.setIcon("\uD83D\uDC03");
        this.setWeightInKilograms(700d);
        this.setMaxAnimalsPerCell(10);
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
