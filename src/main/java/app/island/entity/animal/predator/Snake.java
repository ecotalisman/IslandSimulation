package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.herbivore.Duck;
import app.island.entity.animal.herbivore.Mouse;
import app.island.entity.animal.herbivore.Rabbit;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\snake.yaml")
public class Snake extends Predator implements Runnable {
    public Snake() {
        super(15d, 30, 1, 3d,
                Map.of(Fox.class, 15,
                        Rabbit.class, 20,
                        Mouse.class, 40,
                        Duck.class, 10));
        this.setName("Snake");
        this.setIcon("\uD83D\uDC0D");
        this.setWeightInKilograms(15d);
        this.setMaxAnimalsPerCell(30);
        this.setMaxMovementSpeedPerTurn(1);
        this.setFoodRequiredForFullSatiation(3d);

        this.setPredationProbability(Map.of(
                Fox.class, 15,
                Rabbit.class, 20,
                Mouse.class, 40,
                Duck.class, 10));
    }

    @Override
    protected Predator createNewPredator() {
        return new Snake();
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
