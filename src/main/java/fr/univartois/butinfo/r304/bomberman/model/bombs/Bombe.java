package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.scene.image.Image;

public class Bombe extends AbstractMovable {

    private long timerApparition;
    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public Bombe(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    public long dropBomb() {
        timerApparition = System.currentTimeMillis();
        return timerApparition;
    }

    @Override
    public boolean move(long delta) {
        if (System.currentTimeMillis() - dropBomb() > 4000) {
            game.removeMovable(this);
            Explosion explosion = new Explosion(game, getX(), getY(), new Sprite(new Image("explosion.png")));
            game.addMovable(explosion);
        }
        return true;
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Explosion) {
            this.explode();
        }
    }

    @Override
    public void explode() {
        game.removeMovable(this);
    }

    @Override
    public void hitEnemy() {
        System.out.println("La bombe a touché un ennemi");
    }

}
