package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.herbivore.*;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\bear.yaml")
public class Bear extends Predator {
    public Bear() {
        this.setName("Bear");
        this.setIcon("🐻");
        this.setWeightInKilograms(500d);
        this.setMaxAnimalsPerCell(5);
        this.setMaxMovementSpeedPerTurn(2);
        this.setFoodRequiredForFullSatiation(80d);

        this.setPredationProbability(Map.of(
                Snake.class, 80,
                Horse.class, 40,
                Deer.class, 80,
                Rabbit.class, 80,
                Mouse.class, 90,
                Goat.class, 70,
                Sheep.class, 70,
                Boar.class, 50,
                Buffalo.class, 20,
                Duck.class, 10));
    }
    @Override
    public void eat(Organism organism) {
        Integer value = getPredationProbability().getOrDefault(organism.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
