package app.island.Menu;

import app.island.entity.Organism;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private Map<Organism, AnimalStatus> statusMap = new HashMap<>();

    public void updateStatus(Organism organism, AnimalStatus status) {
        statusMap.put(organism, status);
    }

    public Map<Organism, AnimalStatus> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Organism, AnimalStatus> statusMap) {
        this.statusMap = statusMap;
    }

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
