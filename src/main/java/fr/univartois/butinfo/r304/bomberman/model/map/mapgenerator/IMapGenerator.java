package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;

/**
 * L'interface IMapGenerator définit un générateur de cartes pour le jeu Bomberman
 * qui génère une carte de jeu.
 */
public interface IMapGenerator {
    GameMap genererMap();
}
