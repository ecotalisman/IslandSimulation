package app.island.entity.animal.predator;

import app.island.annotations.Config;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.animal.herbivore.Caterpillar;
import app.island.entity.animal.herbivore.Duck;
import app.island.entity.animal.herbivore.Mouse;
import app.island.entity.animal.herbivore.Rabbit;

import java.util.Map;

@Config(fileName = "config\\entities\\animals\\predator\\snake.yaml")
public class Snake extends Predator {
    private Map<Class<? extends Organism>, Integer> animalsThatCanEat;
    public Snake() {
        this.setName("Snake");
        this.setIcon("\uD83D\uDC0D");
        this.setWeightInKilograms(15d);
        this.setMaxAnimalsPerCell(30);
        this.setMaxMovementSpeedPerTurn(1);
        this.setFoodRequiredForFullSatiation(3d);

        animalsThatCanEat = Map.of(
                Fox.class, 15,
                Rabbit.class, 20,
                Mouse.class, 40,
                Duck.class, 10);
    }
    @Override
    public void eat(Animal animal) {
        Integer value = animalsThatCanEat.getOrDefault(animal.getClass(), -1);
        if (value == -1) {
            System.out.println("Class not found");
        }
    }
}
