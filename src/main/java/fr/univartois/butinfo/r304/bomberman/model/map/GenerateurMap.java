package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;


public class GenerateurMap extends GameMap {


    public GenerateurMap(int height, int width) {
        super(height, width);
    }

    public void genererMap() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                getCells()[i][j] = new Cell(i, j);
            }
        }
    }

    GameMap map = new GameMap(10, 10);
}
