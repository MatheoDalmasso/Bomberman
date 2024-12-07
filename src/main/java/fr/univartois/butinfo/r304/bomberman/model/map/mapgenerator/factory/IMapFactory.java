package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.factory;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;

/**
 * L'interface IMapFactory représente une fabrique de carte dans le jeu Bomberman.
 */
public interface IMapFactory {
    /**
     * Crée une carte.
     *
     * @return La carte créée.
     */
    GameMap createMap();
}
