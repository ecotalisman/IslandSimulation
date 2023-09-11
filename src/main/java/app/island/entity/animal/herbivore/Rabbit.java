package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\rabbit.yaml")
public class Rabbit extends Herbivore {
    private Map<Class<? extends Organism>, Integer> animalsThatCanEat;
    public Rabbit() {
        this.setName("Rabbit");
        this.setIcon("\uD83D\uDC07");
        this.setWeightInKilograms(2d);
        this.setMaxAnimalsPerCell(150);
        this.setMaxMovementSpeedPerTurn(2);
        this.setFoodRequiredForFullSatiation(0.45d);

        animalsThatCanEat = Map.of(
                Plants.class, 100);
    }
    @Override
    public void eat(Animal animal) {
        Integer value = animalsThatCanEat.getOrDefault(animal.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
