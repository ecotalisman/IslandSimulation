package app.island.entity.animal.herbivore;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\herbivore\\mouse.yaml")
public class Mouse extends Herbivore {
    private Map<Class<? extends Organism>, Integer> animalsThatCanEat;
    public Mouse() {
        this.setName("Mouse");
        this.setIcon("\uD83D\uDC01");
        this.setWeightInKilograms(0.05d);
        this.setMaxAnimalsPerCell(500);
        this.setMaxMovementSpeedPerTurn(1);
        this.setFoodRequiredForFullSatiation(0.01d);

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
