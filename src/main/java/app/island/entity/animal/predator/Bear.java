package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.animal.herbivore.*;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\bear.yaml")
public class Bear extends Predator implements Runnable {
    public Bear() {
        super(500d, 5, 2, 80d,
                Map.of(Snake.class, 80,
                        Horse.class, 40,
                        Deer.class, 80,
                        Rabbit.class, 80,
                        Mouse.class, 90,
                        Goat.class, 70,
                        Sheep.class, 70,
                        Boar.class, 50,
                        Buffalo.class, 20,
                        Duck.class, 10));
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
    protected Predator createNewPredator() {
        return new Bear();
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
