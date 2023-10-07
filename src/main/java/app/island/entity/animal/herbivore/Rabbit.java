package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\rabbit.yaml")
public class Rabbit extends Herbivore implements Runnable {
    public Rabbit() {
        super(2d, 150, 2, 0.45d,
                Map.of(Plants.class, 100));
        this.setName("Rabbit");
        this.setIcon("\uD83D\uDC07");
        this.setWeightInKilograms(2d);
        this.setMaxAnimalsPerCell(150);
        this.setMaxMovementSpeedPerTurn(2);
        this.setFoodRequiredForFullSatiation(0.45d);

        this.setPredationProbability(Map.of(
                Plants.class, 100));
    }

    @Override
    protected Herbivore createNewHerbivore() {
        return new Rabbit();
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
