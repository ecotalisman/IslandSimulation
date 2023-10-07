package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\buffalo.yaml")
public class Buffalo extends Herbivore implements Runnable {
    public Buffalo() {
        super(700d,10, 3, 100d,
                Map.of(Plants.class, 100));
        this.setName("Buffalo");
        this.setIcon("\uD83D\uDC03");
        this.setWeightInKilograms(700d);
        this.setMaxAnimalsPerCell(10);
        this.setMaxMovementSpeedPerTurn(3);
        this.setFoodRequiredForFullSatiation(100d);

        this.setPredationProbability(Map.of(
                Plants.class, 100));
    }

    @Override
    protected Herbivore createNewHerbivore() {
        return new Buffalo();
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
