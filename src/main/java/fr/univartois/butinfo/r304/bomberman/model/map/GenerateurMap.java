package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.stage.Stage;

/**
 * La classe {@link GenerateurMap} permet de générer une carte de jeu.
 */
public class GenerateurMap implements IGenerateurMap {
    private final int height;
    private final int width;
    SpriteStore spriteStore = new SpriteStore();
    private Stage stage;
    private BombermanGame game;

    /**
     * Crée un générateur de carte de jeu.
     *
     * @param height La hauteur de la carte à générer.
     * @param width  La largeur de la carte à générer.
     */
    public GenerateurMap(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Génère une carte de jeu.
     *
     * @return La carte de jeu générée.
     */
    @Override
    public GameMap genererMap() {
        GameMap map = new GameMap(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
                    map.setAt(i, j, new Cell(new Wall(spriteStore.getSprite("wall"))));
                } else {
                    if (i % 3 == 0 && j % 3 == 0) {
                        map.setAt(i, j, new Cell(new Wall(spriteStore.getSprite("wall"))));
                    } else {
                        map.setAt(i, j, new Cell(spriteStore.getSprite("lawn")));
                    }
                }
            }
        }
        return map;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setGame(BombermanGame game) {
        this.game = game;
    }
}