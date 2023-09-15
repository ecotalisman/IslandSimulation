package app.island;

import app.island.entity.animal.predator.Bear;
import app.island.entity.plants.Tree;

public class Main {
    public static void main(String[] args) {

        Bear bear = new Bear();
        Tree tree = new Tree();
        System.out.println(bear);
        System.out.println(tree);

    }
}
