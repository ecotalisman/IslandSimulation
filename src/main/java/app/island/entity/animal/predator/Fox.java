package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.herbivore.Caterpillar;
import app.island.entity.animal.herbivore.Duck;
import app.island.entity.animal.herbivore.Mouse;
import app.island.entity.animal.herbivore.Rabbit;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\fox.yaml")
public class Fox extends Predator {
    public Fox() {
        this.setName("Fox");
        this.setIcon("\uD83E\uDD8A");
        this.setWeightInKilograms(8d);
        this.setMaxAnimalsPerCell(30);
        this.setMaxMovementSpeedPerTurn(2);
        this.setFoodRequiredForFullSatiation(2d);

        this.setPredationProbability(Map.of(
                Rabbit.class, 70,
                Mouse.class, 90,
                Duck.class, 60,
                Caterpillar.class, 40));
    }
    @Override
    public void eat(Organism organism) {
        Integer value = getPredationProbability().getOrDefault(organism.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
