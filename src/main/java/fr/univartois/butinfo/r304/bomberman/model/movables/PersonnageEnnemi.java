package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.Random;

public class PersonnageEnnemi extends AbstractMovable {
    Random random = new Random();

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    protected PersonnageEnnemi(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     *
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *        millisecondes).
     *
     * @return
     */
    @Override
    public boolean move(long delta) {
        double newHorizontalSpeed = random.nextDouble() * 2 - 1;
        double newVerticalSpeed = random.nextDouble() * 2 - 1;

        setHorizontalSpeed(newHorizontalSpeed);
        setVerticalSpeed(newVerticalSpeed);

        return super.move(delta);

    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof PersonnageEnnemi) {
            hitEnemy();
        }else{
            explode();
        }
    }

    @Override
    public void explode() {
        game.enemyIsDead(this);
    }

    @Override
    public void hitEnemy() {

    }
}

