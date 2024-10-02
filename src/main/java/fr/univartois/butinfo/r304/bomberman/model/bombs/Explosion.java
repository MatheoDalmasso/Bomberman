package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class Explosion extends AbstractMovable {

    private long dureeExplosion;

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    protected Explosion(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.dureeExplosion = System.currentTimeMillis();
    }

    @Override
    public boolean move(long delta) {
        if (dureeExplosion > 2000) {
            game.removeMovable(this);
            return false;
        }
        return true;
    }

    @Override
    public void collidedWith(IMovable other) {

    }

    @Override
    public void explode() {

    }

    @Override
    public void hitEnemy() {

    }
}
