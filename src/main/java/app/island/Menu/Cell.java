package app.island.Menu;

import app.island.entity.Organism;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Organism> cell = new ArrayList<>();

    public List<Organism> getCell() {
        return cell;
    }

    public void setCell(List<Organism> cell) {
        this.cell = cell;
    }

    public void addAnimal(Organism organism) {
        cell.add(organism);
    }
}
