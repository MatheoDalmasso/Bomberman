/**
 * Package contenant les classes du modèle représentant la carte du jeu.
 */
package fr.univartois.butinfo.r304.bomberman.model.map.wallState;

import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * Classe représentant l'état d'un mur incassable.
 */
public class WallInvincibleState implements IWallState {

    /**
     * Le sprite représentant le mur incassable.
     */
    private final Sprite sprite;

    /**
     * Crée un nouvel état de mur incassable.
     *
     * @param sprite Le sprite représentant le mur incassable.
     */
    public WallInvincibleState(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Dégrade le mur.
     *
     * @param wall le mur à dégrader
     */
    @Override
    public void degrade(Wall wall) {
        // Do nothing, mur incassable
    }

    /**
     * Récupère le sprite associé à l'état du mur.
     *
     * @return le sprite associé à l'état du mur
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
