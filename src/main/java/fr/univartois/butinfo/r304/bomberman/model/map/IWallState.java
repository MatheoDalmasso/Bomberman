/**
 * Package de définition de l'interface IWallState
 */
package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * Interface définissant les états possibles d'un mur
 */
public interface IWallState {
    /**
     * Méthode permettant de dégrader un mur
     *
     * @param wall le mur à dégrader
     */
    void degrade(Wall wall);

    /**
     * Méthode permettant de récupérer le sprite associé à l'état du mur
     *
     * @return le sprite associé à l'état du mur
     */
    Sprite getSprite();

}
