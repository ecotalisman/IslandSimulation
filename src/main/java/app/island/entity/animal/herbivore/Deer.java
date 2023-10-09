package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.plants.Plants;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\deer.yaml")
public class Deer extends Herbivore implements Runnable {
    public Deer() {
        super(300d, 20, 4, 50d,
                Map.of(Plants.class, 100));
        this.setName("Deer");
        this.setIcon("\uD83E\uDD8C");
        this.setWeightInKilograms(300d);
        this.setMaxAnimalsPerCell(20);
        this.setMaxMovementSpeedPerTurn(4);
        this.setFoodRequiredForFullSatiation(50d);

        this.setPredationProbability(Map.of(
                Plants.class, 100));
    }

    @Override
    protected Herbivore createNewHerbivore() {
        return new Deer();
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
