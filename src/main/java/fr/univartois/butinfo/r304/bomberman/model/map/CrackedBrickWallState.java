/**
 * Package contenant les classes du modèle représentant la carte du jeu.
 */
package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * Classe représentant l'état d'un mur en brique fissuré.
 */
public class CrackedBrickWallState implements IWallState {
    private final Sprite sprite;

    /**
     * Crée un nouvel état de mur en brique fissuré.
     *
     * @param sprite Le sprite représentant le mur en brique fissuré.
     */
    public CrackedBrickWallState(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Dégrade le mur.
     *
     * @param wall le mur à dégrader
     */
    @Override
    public void degrade(Wall wall) {
        // Rien à faire, mur déjà dégradé -> devient de l'herbe dans bombe
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
