package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\duck.yaml")
public class Duck extends Herbivore implements Runnable {
    public Duck() {
        super(1d, 200, 4, 0.15d,
                Map.of(Caterpillar.class, 90,
                        Plants.class, 100));
        this.setName("Duck");
        this.setIcon("\uD83E\uDD86");
        this.setWeightInKilograms(1d);
        this.setMaxAnimalsPerCell(200);
        this.setMaxMovementSpeedPerTurn(4);
        this.setFoodRequiredForFullSatiation(0.15d);

        this.setPredationProbability(Map.of(
                Caterpillar.class, 90,
                Plants.class, 100));
    }

    @Override
    protected Herbivore createNewHerbivore() {
        return new Duck();
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
