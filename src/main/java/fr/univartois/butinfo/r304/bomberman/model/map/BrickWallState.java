/**
 * Package qui contient les classes du modèle représentant la carte du jeu.
 */
package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * Classe représentant l'état d'un mur en brique.
 */
public class BrickWallState implements IWallState {

    /**
     * Le sprite représentant le mur en brique.
     */
    private final Sprite sprite;

    SpriteStore spriteStore = SpriteStore.getInstance();


    /**
     * Crée un nouvel état de mur en brique.
     *
     * @param sprite Le sprite représentant le mur en brique.
     */
    public BrickWallState(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Dégrade le mur.
     *
     * @param wall le mur à dégrader
     */
    @Override
    public void degrade(Wall wall) {
        wall.setState(new CrackedBrickWallState(spriteStore.getSprite("cracked-bricks")));
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
