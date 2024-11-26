package fr.univartois.butinfo.r304.bomberman.model.map;

import java.util.Random;

public class GenerateurMap2 extends GenerateurMap{

    private final Random random;
    /**
     * Crée un générateur de carte de jeu.
     *
     * @param height La hauteur de la carte à générer.
     * @param width  La largeur de la carte à générer.
     */
    public GenerateurMap2(int height, int width) {
        super(height, width);
        this.random = new Random();
    }

    @Override
    protected Cell generateCell(int i, int j) {
        IWallState state;
        if (isBorder(i, j)) {
            state = new WallInvincibleState(spriteStore.getSprite("wall"));
        } else if (isWallPosition(i, j)) {
            state = new WallInvincibleState(spriteStore.getSprite("wall"));
        } else if (random.nextInt(100) < 15) {
            state = new BrickWallState(spriteStore.getSprite("bricks"));
        } else if (random.nextInt(100) < 7) {
            state = new CrackedBrickWallState(spriteStore.getSprite("cracked-bricks"));
        } else {
            return new Cell(spriteStore.getSprite("lawn"));
        }
        return new Cell(new Wall(state));
    }

    private boolean isBorder(int i, int j) {
        return i == 0 || j == 0 || i == getHeight() - 1 || j == getWidth() - 1;
    }

    private boolean isWallPosition(int i, int j) {
        return i % 3 == 0 && j % 3 == 0;
    }
}
