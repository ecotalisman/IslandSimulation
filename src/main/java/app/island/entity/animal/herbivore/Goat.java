package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.plants.Plants;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\goat.yaml")
public class Goat extends Herbivore implements Runnable {
    public Goat() {
        super(60d, 140, 3, 10d,
                Map.of(Plants.class, 100));
        this.setName("Goat");
        this.setIcon("\uD83D\uDC10");
        this.setWeightInKilograms(60d);
        this.setMaxAnimalsPerCell(140);
        this.setMaxMovementSpeedPerTurn(3);
        this.setFoodRequiredForFullSatiation(10d);

        this.setPredationProbability(Map.of(
                Plants.class, 100));
    }

    @Override
    protected Herbivore createNewHerbivore() {
        return new Goat();
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
