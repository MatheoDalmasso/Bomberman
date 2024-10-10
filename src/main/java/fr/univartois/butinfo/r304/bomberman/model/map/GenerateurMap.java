package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class GenerateurMap {
    private final int height;
    private final int width;
    private final Cell[][] cells;
    private GameMap map;
    SpriteStore spriteStore = new SpriteStore();


    public GenerateurMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Cell[height][width];
    }


    public GameMap genererMap() {
        map = new GameMap(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(i==0 || j==0|| i==height-1 || j==width-1){
                    map.setAt(i, j, new Cell(new Wall(spriteStore.getSprite("wall"))));
                }
                else{
                    if(i%3==0 && j%3==0){
                        map.setAt(i, j, new Cell(new Wall(spriteStore.getSprite("wall"))));
                    }
                    else {
                        map.setAt(i, j, new Cell(spriteStore.getSprite("lawn")));
                    }
                }

            }
        }
        return map;
    }

}