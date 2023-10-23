package app.island.entity.animal.predator;

import app.island.Menu.Cell;
import app.island.Menu.IslandSimulation;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.List;
import java.util.Map;

import static app.island.Constants.Constants.*;

public abstract class Predator extends Animal {
    private final double initialWeight;
    private final Object lock = new Object();

    public Predator(double weightInKilograms, int maxAnimalsPerCell, int maxMovementSpeedPerTurn, double foodRequiredForFullSatiation,
                     Map<Class<? extends Organism>, Integer> predationProbability) {
        setWeightInKilograms(weightInKilograms);
        setMaxAnimalsPerCell(maxAnimalsPerCell);
        setMaxMovementSpeedPerTurn(maxMovementSpeedPerTurn);
        setFoodRequiredForFullSatiation(foodRequiredForFullSatiation);
        setPredationProbability(predationProbability);
        this.initialWeight = weightInKilograms;
    }

    @Override
    public void eat(Organism organism) {
        synchronized (lock) {
            double organismWeight = 0;
            if (organism instanceof Plants) {
                if (!((Plants) organism).isAlive()) return;
                organismWeight = ((Plants) organism).getWeightInKilograms();
            } else if (organism instanceof Animal) {
                if (!((Animal) organism).isAlive()) return;
                organismWeight = ((Animal) organism).getWeightInKilograms();
            }

            Integer value = getPredationProbability().getOrDefault(organism.getClass(), -1);
            if (value == -1) {
                return;
            }

            int chance = IslandSimulation.random.nextInt(101);
            if (value >= chance) {
                double foodEaten = Math.min(Math.min(getWeightInKilograms(), getFoodRequiredForFullSatiation()), organismWeight);
                if (organism instanceof Animal) {
                    setWeightBasedOnFoodEaten(foodEaten);
                    ((Animal) organism).setAlive(false);
                } else if (organism instanceof Plants) {
                    setWeightBasedOnFoodEaten(foodEaten);
                    if (((Plants) organism).getWeightInKilograms() <= 0) {
                        ((Plants) organism).setAlive(false);
                    }
                }
            }
        }
    }

    private void setWeightBasedOnFoodEaten(double foodEaten) {
        double newWeight = getWeightInKilograms() + foodEaten;
        if (newWeight > initialWeight) {
            setWeightInKilograms(initialWeight);
        } else {
            setWeightInKilograms(newWeight);
        }
    }


    @Override
    public void chooseDirection() {
        synchronized (lock) {
            int[] newLocation = calculateNewLocation();
            if (newLocation != null) {
                moveAndEat(newLocation[0], newLocation[1]);
            }
        }
    }
    private int[] calculateNewLocation() {
        int[] location = findLocationOf(this);
        if (location == null) {
            return null;
        }
        int maxShift = getMaxMovementSpeedPerTurn();

        int shiftX = IslandSimulation.random.nextInt(2 * maxShift + 1) - maxShift;
        int shiftY = IslandSimulation.random.nextInt(2 * maxShift + 1) - maxShift;

        int newX = location[0] + shiftX;
        int newY = location[1] + shiftY;

        if (isValidCoordinates(newX, newY)) {
            return new int[]{newX, newY};
        }
        return null;
    }

    private boolean isValidCoordinates(int x, int y) {
        return x >= 0 && x < NUM_ROWS && y >= 0 && y < NUM_COLUMNS;
    }

    private int[] findLocationOf(Organism target) {
        for (int x = 0; x < NUM_ROWS; x++) {
            for (int y = 0; y < NUM_COLUMNS; y++) {
                List<Organism> organismList = IslandSimulation.getCellAt(x, y).getCell();
                if (organismList.contains(target)) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

    private void moveAndEat(int newX, int newY) {
        int[] currentLocation = findLocationOf(this);
        Cell currentCell = IslandSimulation.getCellAt(currentLocation[0], currentLocation[1]);
        Cell newCell = IslandSimulation.getCellAt(newX, newY);

        synchronized (lock) {
            currentCell.getCell().remove(this);
            newCell.getCell().add(this);

            List<Organism> organismsInCell = newCell.getCell();
            if (organismsInCell.isEmpty()) {
                setWeightInKilograms(getWeightInKilograms() * WEIGHT_DECREASE_FACTOR_PREDATOR);
                if (getWeightInKilograms() < initialWeight * MIN_WEIGHT_FOR_SURVIVAL_PREDATOR) {
                    setAlive(false);
                }
                return;
            }

            for (Organism organism : organismsInCell) {
                if (organism.getClass().equals(this.getClass())) {
                    reproduce();
                } else {
                    eat(organism);
                }
            }
        }

        IslandSimulation.setCellAt(currentLocation[0], currentLocation[1], currentCell);
        IslandSimulation.setCellAt(newX, newY, newCell);
    }


    @Override
    public void reproduce() {
        int entityCount = 0;
        Cell currentCell = IslandSimulation.getCellAt(findLocationOf(this)[0], findLocationOf(this)[1]);
        for (Organism organism : currentCell.getCell()) {
            if (organism.getClass().equals(this.getClass())) {
                entityCount++;
            }
        }

        if (entityCount < getMaxAnimalsPerCell()) {
            currentCell.getCell().add(createNewPredator());
        }
    }

    protected abstract Predator createNewPredator();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predator predator = (Predator) o;

        if (Double.compare(initialWeight, predator.initialWeight) != 0) return false;
        return lock.equals(predator.lock);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(initialWeight);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + lock.hashCode();
        return result;
    }
}
