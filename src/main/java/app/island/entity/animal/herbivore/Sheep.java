package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\sheep.yaml")
public class Sheep extends Herbivore {
    private Map<Class<? extends Organism>, Integer> animalsThatCanEat;
    public Sheep() {
        this.setName("Sheep");
        this.setIcon("\uD83D\uDC11");
        this.setWeightInKilograms(70d);
        this.setMaxAnimalsPerCell(140);
        this.setMaxMovementSpeedPerTurn(3);
        this.setFoodRequiredForFullSatiation(15d);

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
