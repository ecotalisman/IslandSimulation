package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.herbivore.*;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\eagle.yaml")
public class Eagle extends Predator {
    public Eagle() {
        this.setName("Eagle");
        this.setIcon("\uD83E\uDD85");
        this.setWeightInKilograms(6d);
        this.setMaxAnimalsPerCell(20);
        this.setMaxMovementSpeedPerTurn(3);
        this.setFoodRequiredForFullSatiation(1d);

        this.setPredationProbability(Map.of(
                Fox.class, 10,
                Rabbit.class, 90,
                Mouse.class, 90,
                Duck.class, 80));
    }
    @Override
    public void eat(Organism organism) {
        Integer value = getPredationProbability().getOrDefault(organism.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
