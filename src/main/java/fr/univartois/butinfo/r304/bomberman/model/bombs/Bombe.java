package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.scene.image.Image;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Bombe extends AbstractMovable {
    private static final Logger LOGGER = LogManager.getLogManager().getLogger(Bombe.class.getPackageName());
    private long delai;
    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public Bombe(BombermanGame game, double xPosition, double yPosition, Sprite sprite, long delai) {
        super(game, xPosition, yPosition, sprite);
        this.delai = delai;
    }

    public void poseBombe() {
        double x=this.getX();
        double y=this.getY();
        delai = System.currentTimeMillis();
        new Bombe(game, x, y, new Sprite(new Image("./../sprites/bomb.png")), delai);
    }

    @Override
    public boolean move(long delta) {
        if (System.currentTimeMillis() - delai > 4000) {
            for (double i=-1; i<2; i++) {
                for (double j=-1; j<2; j++) {
                    if( i == 0 && j == 0) {
                        continue;
                    }
                    Explosion explosion = new Explosion(game, getX() + i, getY() + j, new Sprite(new Image("./../sprites/explosion.png")));
                    game.addMovable(explosion);
                    Explosion pelouse = new Explosion(game, getX() + i, getY() + j, new Sprite(new Image("./../sprites/lawn.png")));
                    game.addMovable(pelouse);
                }
            }
            game.removeMovable(this);
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
        LOGGER.info("La bombe a touché un ennemi");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
