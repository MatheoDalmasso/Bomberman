package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator;

import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.MapGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.BrickWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.CrackedBrickWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.WallInvincibleState;

import java.util.Random;

public class MapGenerator4 extends MapGenerator {

    private final Random random;

    /**
     * Crée un générateur de carte de jeu.
     *
     * @param height La hauteur de la carte à générer.
     * @param width  La largeur de la carte à générer.
     */
    public MapGenerator4(int height, int width) {
        super(height, width);
        random = new Random();
    }

    @Override
    protected Cell generateCell(int i, int j) {
        IWallState state;
        if (isBorderOfMap(i, j)) {
            state = new WallInvincibleState(spriteStore.getSprite("wall"));
        } else if (isPositionAWall()) {
            state = new WallInvincibleState(spriteStore.getSprite("wall"));
        } else if (random.nextInt(100) < 30) {
            state = new BrickWallState(spriteStore.getSprite("bricks"));
        } else if (random.nextInt(100) < 15) {
            state = new CrackedBrickWallState(spriteStore.getSprite("cracked-bricks"));
        } else {
            return new Cell(spriteStore.getSprite("lawn"));
        }
        return new Cell(new Wall(state, i * spriteStore.getSpriteSize(), j * spriteStore.getSpriteSize()));
    }

    private boolean isBorderOfMap(int i, int j) {
        return i == 0 || j == 0 || i == getHeight() - 1 || j == getWidth() - 1;
    }

    private boolean isPositionAWall() {
        return random.nextInt(100) < 15;
    }
}
