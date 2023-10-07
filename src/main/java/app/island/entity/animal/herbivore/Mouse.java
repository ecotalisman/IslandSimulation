package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;
import app.island.exceptions.IslandException;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\mouse.yaml")
public class Mouse extends Herbivore implements Runnable {
    public Mouse() {
        super(0.05d, 500, 1, 0.01d,
                Map.of(Caterpillar.class, 90,
                        Plants.class, 100));
        this.setName("Mouse");
        this.setIcon("\uD83D\uDC01");
        this.setWeightInKilograms(0.05d);
        this.setMaxAnimalsPerCell(500);
        this.setMaxMovementSpeedPerTurn(1);
        this.setFoodRequiredForFullSatiation(0.01d);

        this.setPredationProbability(Map.of(
                Caterpillar.class, 90,
                Plants.class, 100));
    }

    @Override
    protected Herbivore createNewHerbivore() {
        return new Mouse();
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
