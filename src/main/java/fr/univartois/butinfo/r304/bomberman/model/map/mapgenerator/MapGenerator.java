package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator;

import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * La classe {@link MapGenerator} permet de générer une carte de jeu.
 */
public abstract class MapGenerator implements IMapGenerator {
    private final int height;
    private final int width;
    protected SpriteStore spriteStore = SpriteStore.getInstance();

    /**
     * Crée un générateur de carte de jeu.
     *
     * @param height La hauteur de la carte à générer.
     * @param width  La largeur de la carte à générer.
     */
    protected MapGenerator(int height, int width) {
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
                map.setAt(i, j, generateCell(i, j));
            }
        }
        return map;
    }

    /**
     * Génère une cellule pour une position donnée.
     *
     * @param i La position en hauteur.
     * @param j La position en largeur.
     * @return La cellule générée.
     */
    protected abstract Cell generateCell(int i, int j);

    /**
     * Récupère la hauteur de la carte.
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Récupère la largeur de la carte.
     * @return
     */
    public int getWidth() {
        return width;
    }
}