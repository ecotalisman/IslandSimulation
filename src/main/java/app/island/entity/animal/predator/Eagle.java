package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.animal.herbivore.*;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\eagle.yaml")
public class Eagle extends Predator implements Runnable {
    public Eagle() {
        super(6d, 20, 3, 1d,
                Map.of(Fox.class, 10,
                        Rabbit.class, 90,
                        Mouse.class, 90,
                        Duck.class, 80));
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
    protected Predator createNewPredator() {
        return new Eagle();
    }

    @Override
    public void run() {
        while (isAlive()) {
            chooseDirection();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IslandException("Error while waiting", e);
            }
        }
    }
}
