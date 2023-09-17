package app.island.entity.animal.herbivore;

import app.island.Menu.Cell;
import app.island.Menu.Menu;
import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.plants.Plants;

import java.util.List;
import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\boar.yaml")
public class Boar extends Herbivore implements Runnable {
    public Boar() {
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
    public void eat(Organism organism) {
        Integer value = getPredationProbability().getOrDefault(organism.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }

    @Override
    public void chooseDirection() {
        Cell[][] field = Menu.field;
        int x = Menu.random.nextInt();
        int y = Menu.random.nextInt();
        validateCoordinates(x, y);

        Cell cell = field[x][y];
        List<Organism> organismList = cell.getCell();

        eat(organismList.get(0));

    }

    private void validateCoordinates(int x, int y) {
    }

    @Override
    public void run() {
        while (isAlive()) {
            chooseDirection();
        }
    }
}
