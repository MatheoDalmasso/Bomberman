package fr.univartois.butinfo.r304.bomberman.model.bonus;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.Joueur;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class RecupVie extends AbstractMovable implements IWallState {

    private SpriteStore spriteStore = new SpriteStore();

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public RecupVie(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Joueur joueur) {
            joueur.addLife(1); // Methode qui ajout la vie à mettre dans imovable
            game.removeMovable(this);
        }
    }

    @Override
    public void explode() {
        //
    }

    @Override
    public void hitEnemy() {
        //
    }

    @Override
    public void degrade(Wall wall) {
        // Ne fait Rien
    }
}
