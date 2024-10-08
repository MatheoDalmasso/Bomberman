package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.scene.image.Image;


public class GenerateurMap {
    private final int height;
    private final int width;
    private final Cell[][] cells;

    public GenerateurMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
        genererMap();
    }

    public void genererMap() {
        GameMap map = new GameMap(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(i==0 || j==0|| i==height-1 || j==width-1){
                    cells[i][j]= new Cell(new Sprite(new Image("/fr/univartois/butinfo/r304/bomberman/view/sprites/wall.png"))));
                }
                else{
                    if(i%2==0 && j%2==0){
                        cells[i][j] = new Cell(new Sprite(new Image("/fr/univartois/butinfo/r304/bomberman/view/sprites/wall.png"))));
                    }
                    else {
                        cells[i][j]= new Cell(new Sprite(new Image("/fr/univartois/butinfo/r304/bomberman/view/sprites/lawn.png"))));
                    }
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map.setAt(i, j, cells[i][j]);
            }
        }
    }
}
