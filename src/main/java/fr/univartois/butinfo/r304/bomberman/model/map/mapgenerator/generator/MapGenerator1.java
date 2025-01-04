package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator;

import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.MapGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.BrickWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.CrackedBrickWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.WallInvincibleState;

import java.security.SecureRandom;

/**
 * La classe MapGenerator1 génère une carte pour le jeu Bomberman en utilisant une logique spécifique.
 */
public class MapGenerator1 extends MapGenerator {

    /**
     * Le générateur de nombres aléatoires utilisé pour générer la carte.
     */
    private final SecureRandom random;

    /**
     * Crée un générateur de carte de jeu.
     *
     * @param height La hauteur de la carte à générer.
     * @param width  La largeur de la carte à générer.
     */
    public MapGenerator1(int height, int width) {
        super(height, width);
        this.random = new SecureRandom();
    }

    /**
     * Génère une cellule de la carte à la position spécifiée.
     *
     * @param i La position en y de la cellule.
     * @param j La position en x de la cellule.
     * @return La cellule générée.
     */
    @Override
    protected Cell generateCell(int i, int j) {
        IWallState state;
        if (isBorderOfMap(i, j)) {
            state = new WallInvincibleState(spriteStore.getSprite("wall"));
        } else if (MapGenerator.isPositionAWall(i, j)) {
            state = new WallInvincibleState(spriteStore.getSprite("wall"));
        } else if (random.nextInt(100) < 6) {
            state = new BrickWallState(spriteStore.getSprite("bricks"));
        } else if (random.nextInt(100) < 3) {
            state = new CrackedBrickWallState(spriteStore.getSprite("cracked-bricks"));
        } else {
            return new Cell(spriteStore.getSprite("lawn"));
        }
        return new Cell(new Wall(state, j * spriteStore.getSpriteSize(), i * spriteStore.getSpriteSize()));
    }

    /**
     * Vérifie si la position spécifiée est une bordure de la carte.
     *
     * @param i La position en y.
     * @param j La position en x.
     * @return true si la position est une bordure, sinon false.
     */
    private boolean isBorderOfMap(int i, int j) {
        return i == 0 || j == 0 || i == getHeight() - 1 || j == getWidth() - 1;
    }


}