package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\caterpillar.yaml")
public class Caterpillar extends Herbivore implements Runnable {
    public Caterpillar() {
        super(0.01d, 1000, 0, 0,
                Map.of(Plants.class, 100));
        this.setName("Caterpillar");
        this.setIcon("\uD83D\uDC1B");
        this.setWeightInKilograms(0.01d);
        this.setMaxAnimalsPerCell(1000);
        this.setMaxMovementSpeedPerTurn(0);
        this.setFoodRequiredForFullSatiation(0);

        this.setPredationProbability(Map.of(
                Plants.class, 100));
    }

    @Override
    protected Herbivore createNewHerbivore() {
        return new Caterpillar();
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
