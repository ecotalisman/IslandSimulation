package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\boar.yaml")
public class Boar extends Herbivore {
    public Boar() {
        this.setName("Boar");
        this.setIcon("\uD83D\uDC17");
        this.setWeightInKilograms(400d);
        this.setMaxAnimalsPerCell(50);
        this.setMaxMovementSpeedPerTurn(2);
        this.setFoodRequiredForFullSatiation(50d);

        this.setPredationProbability(Map.of(
                Mouse.class, 50,
                Caterpillar.class, 90,
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
