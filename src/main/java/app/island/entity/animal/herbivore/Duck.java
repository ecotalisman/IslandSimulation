package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\duck.yaml")
public class Duck extends Herbivore {
    private Map<Class<? extends Organism>, Integer> animalsThatCanEat;
    public Duck() {
        this.setName("Duck");
        this.setIcon("\uD83E\uDD86");
        this.setWeightInKilograms(1d);
        this.setMaxAnimalsPerCell(200);
        this.setMaxMovementSpeedPerTurn(4);
        this.setFoodRequiredForFullSatiation(0.15d);

        animalsThatCanEat = Map.of(
                Caterpillar.class, 90,
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
