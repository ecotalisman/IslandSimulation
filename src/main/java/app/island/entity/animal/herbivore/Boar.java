package app.island.entity.animal.herbivore;

import app.island.Menu.Cell;
import app.island.Menu.Menu;
import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;
import app.island.exceptions.IslandException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static app.island.Constants.Constants.*;

@Config(fileName = "config\\entities\\animals\\herbivore\\boar.yaml")
public class Boar extends Herbivore implements Runnable {

    public Boar() {
        super(400d, 50, 2,50d,
                Map.of( Mouse.class, 50,
                        Caterpillar.class, 90,
                        Plants.class, 100));
        this.setName("Boar");
        this.setIcon("\uD83D\uDC17");
        this.setWeightInKilograms(400d);
        this.setMaxAnimalsPerCell(50);
        this.setMaxMovementSpeedPerTurn(2);
        this.setFoodRequiredForFullSatiation(50d);

        this.setPredationProbability(Map.of(
                Mouse.class, 50,
                Caterpillar.class, 90,
                Plants.class, 100));
    }

    @Override
    protected Herbivore createNewHerbivore() {
        return new Boar();
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
