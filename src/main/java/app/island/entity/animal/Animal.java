package app.island.entity.animal;

import app.island.entity.Organism;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Animal extends Organism implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private String icon;
        private double weightInKilograms;
        private int maxAnimalsPerCell;
        private int maxMovementSpeedPerTurn;
        private double foodRequiredForFullSatiation;
        private Map<Class<? extends Organism>, Integer> predationProbability;
        private boolean isAlive = true;

        public void eat(Animal animal) {

        }

        public void reproduce() {

        }

        public void chooseDirection() {

        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getIcon() {
                return icon;
        }

        public void setIcon(String icon) {
                this.icon = icon;
        }

        public double getWeightInKilograms() {
                return weightInKilograms;
        }

        public void setWeightInKilograms(double weightInKilograms) {
                this.weightInKilograms = weightInKilograms;
        }

        public int getMaxAnimalsPerCell() {
                return maxAnimalsPerCell;
        }

        public void setMaxAnimalsPerCell(int maxAnimalsPerCell) {
                this.maxAnimalsPerCell = maxAnimalsPerCell;
        }

        public int getMaxMovementSpeedPerTurn() {
                return maxMovementSpeedPerTurn;
        }

        public void setMaxMovementSpeedPerTurn(int maxMovementSpeedPerTurn) {
                this.maxMovementSpeedPerTurn = maxMovementSpeedPerTurn;
        }

        public double getFoodRequiredForFullSatiation() {
                return foodRequiredForFullSatiation;
        }

        public void setFoodRequiredForFullSatiation(double foodRequiredForFullSatiation) {
                this.foodRequiredForFullSatiation = foodRequiredForFullSatiation;
        }

        public Map<Class<? extends Organism>, Integer> getPredationProbability() {
                return predationProbability;
        }

        public void setPredationProbability(Map<Class<? extends Organism>, Integer> predationProbability) {
                this.predationProbability = predationProbability;
        }

        public boolean isAlive() {
                return isAlive;
        }

        public void setAlive(boolean alive) {
                isAlive = alive;
        }


        @Override
        public String toString() {
                return "Animal{" +
                        "name='" + name + '\'' +
                        ", icon='" + icon + '\'' +
                        ", weightInKilograms=" + weightInKilograms +
                        ", maxAnimalsPerCell=" + maxAnimalsPerCell +
                        ", maxMovementSpeedPerTurn=" + maxMovementSpeedPerTurn +
                        ", foodRequiredForFullSatiation=" + foodRequiredForFullSatiation +
                        ", predationProbability=" + predationProbability +
                        ", isAlive=" + isAlive +
                        "} " + super.toString();
        }
}
