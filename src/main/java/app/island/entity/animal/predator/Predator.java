package app.island.entity.animal.predator;

import app.island.Menu.Cell;
import app.island.Menu.Menu;
import app.island.entity.Organism;
import app.island.entity.animal.Animal;
import app.island.entity.plants.Plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static app.island.Constants.Constants.*;

public abstract class Predator extends Animal {
    private final double currentWeight;
    private List<Organism> organismToAdd = new ArrayList<>();
    private final Object lock = new Object();

    public Predator(double weightInKilograms, int maxAnimalsPerCell, int maxMovementSpeedPerTurn, double foodRequiredForFullSatiation,
                     Map<Class<? extends Organism>, Integer> predationProbability) {
        setWeightInKilograms(weightInKilograms);
        setMaxAnimalsPerCell(maxAnimalsPerCell);
        setMaxMovementSpeedPerTurn(maxMovementSpeedPerTurn);
        setFoodRequiredForFullSatiation(foodRequiredForFullSatiation);
        setPredationProbability(predationProbability);
        this.currentWeight = weightInKilograms;
    }

    @Override
    public void eat(Organism organism) {
        synchronized (lock) {
            if (organism instanceof Animal && !((Animal) organism).isAlive()) {
                return;
            }

            Integer value = getPredationProbability().getOrDefault(organism.getClass(), -1);
            if (value == -1) {
                return;
            }

            int chance = Menu.random.nextInt(101);
            if (value >= chance) {
                double foodEaten = currentWeight - getWeightInKilograms();
                if (((Animal) organism).isAlive()) {
                    setWeightBasedOnFoodEaten(foodEaten, ((Animal) organism).getWeightInKilograms());
                    ((Animal) organism).setAlive(false);
                } else if (((Plants) organism).isAlive()) {
                    setWeightBasedOnFoodEaten(foodEaten, ((Plants) organism).getWeightInKilograms());
                    if (((Plants) organism).getWeightInKilograms() <= 0) {
                        ((Plants) organism).setAlive(false);
                    }
                }
            }
        }
    }

    @Override
    public void chooseDirection() {
        synchronized (lock) {
            Cell[][] field = Menu.field;
            int[] location = findLocation(field);

            if (location != null) {
                int maxX = location[0] + getMaxMovementSpeedPerTurn();
                int maxY = location[1] + getMaxMovementSpeedPerTurn();

                if (maxX <= 0 || maxY <= 0) {
                    return;
                }

                int newX = location[0] + Menu.random.nextInt(maxX);
                int newY = location[1] + Menu.random.nextInt(maxY);

                if (isValidCoordinates(newX, newY)) {
                    synchronized (field) {
                        moveAndEat(newX, newY, field);
                    }
                } else {
                    chooseDirection();
                }
            }
        }
    }

    private boolean isValidCoordinates(int x, int y) {
        return x >= 0 && x < NUM_ROWS && y >= 0 && y < NUM_COLUMNS;
    }

    private int[] findLocation(Cell[][] field) {
        synchronized (lock) {
            for (int x = 0; x < NUM_ROWS; x++) {
                for (int y = 0; y < NUM_COLUMNS; y++) {
                    List<Organism> organismList = field[x][y].getCell();
                    synchronized (organismList) {
                        for (Organism organism : organismList) {
                            if (organism.getClass().equals(this.getClass())) {
                                return new int[]{x, y};
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    private void moveAndEat(int newX, int newY, Cell[][] field) {
        Cell newCell = field[newX][newY];
        synchronized (lock) {
            setOrganismToAdd(newCell.getCell());

            if (getOrganismToAdd().isEmpty()) {
                setWeightInKilograms(getWeightInKilograms() * WEIGHT_DECREASE_FACTOR);
                if (getWeightInKilograms() < currentWeight * MIN_WEIGHT_FOR_SURVIVAL) {
                    setAlive(false);
                }
                return;
            }

            synchronized (getOrganismToAdd()) {
                List<Organism> copy = new ArrayList<>(getOrganismToAdd());
                for (Organism organism : copy) {
                    if (organism.getClass().equals(this.getClass())) {
                        reproduce();
                    } else {
                        synchronized (organism) {
                            eat(organism);
                        }
                    }
                }
            }

            synchronized (Menu.field) {
                Menu.field[newX][newY].setCell(organismToAdd);
            }
        }
    }

    @Override
    public void reproduce() {
        int entityCount = 0;
        synchronized (lock) {
            for (Organism organism : this.getOrganismToAdd()) {
                if (organism.getClass().equals(this.getClass())) {
                    entityCount++;
                }
            }

            if (entityCount < getMaxAnimalsPerCell()) {
                this.getOrganismToAdd().add(createNewPredator());
            }
        }
    }

    protected abstract Predator createNewPredator();

    private void setWeightBasedOnFoodEaten(double foodEaten, double organismWeight) {
        if (foodEaten >= organismWeight) {
            setWeightInKilograms(currentWeight);
        } else {
            setWeightInKilograms(organismWeight);
        }
    }

    public synchronized List<Organism> getOrganismToAdd() {
        return organismToAdd;
    }

    public synchronized void setOrganismToAdd(List<Organism> organismToAdd) {
        this.organismToAdd = organismToAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predator predator = (Predator) o;

        if (Double.compare(currentWeight, predator.currentWeight) != 0) return false;
        if (!organismToAdd.equals(predator.organismToAdd)) return false;
        return lock.equals(predator.lock);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(currentWeight);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + organismToAdd.hashCode();
        result = 31 * result + lock.hashCode();
        return result;
    }
}
