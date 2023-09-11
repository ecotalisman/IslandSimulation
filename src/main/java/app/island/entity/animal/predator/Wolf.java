package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.animal.herbivore.*;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\wolf.yaml")
public class Wolf extends Predator {
    private Map<Class<? extends Organism>, Integer> animalsThatCanEat;
    public Wolf() {
        this.setName("Wolf");
        this.setIcon("\uD83D\uDC3A");
        this.setWeightInKilograms(50d);
        this.setMaxAnimalsPerCell(30);
        this.setMaxMovementSpeedPerTurn(3);
        this.setFoodRequiredForFullSatiation(8d);

        animalsThatCanEat = Map.of(
                Horse.class, 10,
                Deer.class, 15,
                Rabbit.class, 60,
                Mouse.class, 80,
                Goat.class, 60,
                Sheep.class, 70,
                Boar.class, 15,
                Buffalo.class, 10,
                Duck.class, 40);
    }
    @Override
    public void eat(Animal animal) {
        Integer value = animalsThatCanEat.getOrDefault(animal.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
