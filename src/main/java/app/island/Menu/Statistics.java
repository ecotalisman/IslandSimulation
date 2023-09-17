package app.island.Menu;

import app.island.entity.Organism;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    Map<Organism, AnimalStatus> statusMap = new HashMap<>();

    @Override
    public String toString() {
        return "Statistics{" +
                "statusMap=" + statusMap +
                '}';
    }

    class AnimalStatus {
        boolean isAlive;
        double weightInKilograms;

        @Override
        public String toString() {
            return "AnimalStatus{" +
                    "isAlive=" + isAlive +
                    ", weightInKilograms=" + weightInKilograms +
                    '}';
        }
    }

}
