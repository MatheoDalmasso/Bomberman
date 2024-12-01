package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.factory;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator1;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;

/**
 * La classe MapFactory1 est une fabrique de cartes pour le jeu Bomberman.
 * Elle utilise le générateur de cartes MapGenerator1 pour créer une carte.
 */
public class MapFactory1 implements IMapFactory {

    /**
     * La hauteur et la largeur de la carte en pixels.
     */
    private final int height;

    /**
     * La largeur de la carte en pixels.
     */
    private final int width;

    /**
     * Le magasin de sprites utilisé pour la carte.
     */
    private final ISpriteStore spriteStore;

    /**
     * Crée une nouvelle instance de MapFactory1.
     *
     * @param height      La hauteur de la carte en pixels.
     * @param width       La largeur de la carte en pixels.
     * @param spriteStore Le magasin de sprites utilisé pour la carte.
     */
    public MapFactory1(int height, int width, ISpriteStore spriteStore) {
        this.height = height;
        this.width = width;
        this.spriteStore = spriteStore;
    }

    /**
     * Crée une carte en utilisant le générateur de cartes MapGenerator1.
     *
     * @return La carte générée.
     */
    @Override
    public GameMap createMap() {
        return new MapGenerator1(height / spriteStore.getSpriteSize(), width / spriteStore.getSpriteSize()).genererMap();
    }
}