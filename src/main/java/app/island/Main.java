package app.island;

import app.island.entity.animal.herbivore.Deer;
import app.island.entity.animal.herbivore.Horse;
import app.island.entity.animal.predator.Wolf;
import app.island.entity.plants.Grass;
import app.island.yamlreader.YamlReader;

public class Main {
    public static void main(String[] args) {

        YamlReader yamlReader = YamlReader.getInstance();

        Deer deer = yamlReader.loadConfiguredObject(Deer.class);
        Horse horse = yamlReader.loadConfiguredObject(Horse.class);
        Wolf wolf = yamlReader.loadConfiguredObject(Wolf.class);
        Grass grass = yamlReader.loadConfiguredObject(Grass.class);

        System.out.println(deer);
        System.out.println(horse);
        System.out.println(wolf);
        System.out.println(grass);
    }
}