package app.island.entity.animal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Animal implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private int weightInKilograms;
        private int maxAnimalsPerCell;
        private int maxMovementSpeedPerTurn;
        private int foodRequiredForFullSatiation;
        private Map<String, Integer>  predationProbability = new HashMap<>();

        public void eat() {

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

        public int getWeightInKilograms() {
                return weightInKilograms;
        }

        public void setWeightInKilograms(int weightInKilograms) {
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

        public int getFoodRequiredForFullSatiation() {
                return foodRequiredForFullSatiation;
        }

        public void setFoodRequiredForFullSatiation(int foodRequiredForFullSatiation) {
                this.foodRequiredForFullSatiation = foodRequiredForFullSatiation;
        }

        public Map<String, Integer> getPredationProbability() {
                return predationProbability;
        }

        public void setPredationProbability(Map<String, Integer> predationProbability) {
                this.predationProbability = predationProbability;
        }

        @Override
        public String toString() {
                return "Animal{" +
                        "name='" + name + '\'' +
                        ", weightInKilograms=" + weightInKilograms +
                        ", maxAnimalsPerCell=" + maxAnimalsPerCell +
                        ", maxMovementSpeedPerTurn=" + maxMovementSpeedPerTurn +
                        ", foodRequiredForFullSatiation=" + foodRequiredForFullSatiation +
                        ", predationProbability=" + predationProbability +
                        '}';
        }
}
