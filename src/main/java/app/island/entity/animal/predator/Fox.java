package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.animal.herbivore.Caterpillar;
import app.island.entity.animal.herbivore.Duck;
import app.island.entity.animal.herbivore.Mouse;
import app.island.entity.animal.herbivore.Rabbit;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\fox.yaml")
public class Fox extends Predator {
    private Map<Class<? extends Organism>, Integer> animalsThatCanEat;
    public Fox() {
        this.setName("Fox");
        this.setIcon("\uD83E\uDD8A");
        this.setWeightInKilograms(8d);
        this.setMaxAnimalsPerCell(30);
        this.setMaxMovementSpeedPerTurn(2);
        this.setFoodRequiredForFullSatiation(2d);

        animalsThatCanEat = Map.of(
                Rabbit.class, 70,
                Mouse.class, 90,
                Duck.class, 60,
                Caterpillar.class, 40);
    }
    @Override
    public void eat(Animal animal) {
        Integer value = animalsThatCanEat.getOrDefault(animal.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
