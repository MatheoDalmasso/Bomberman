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
    public PersonnageEnnemi(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
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
        double newHorizontalSpeed = random.nextDouble() * 2 ;
        double newVerticalSpeed = random.nextDouble() * 2 ;

        setHorizontalSpeed(newHorizontalSpeed);
        setVerticalSpeed(newVerticalSpeed);

        return super.move(delta);

    }

    /**
     * Permet de gérer les collisions de cet objet avec un autre objet.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof PersonnageEnnemi) {
            hitEnemy();
        }else{
            explode();
        }
    }

    /**
     * Permet de gérer l'explosion de cet objet.
     */
    @Override
    public void explode() {
        game.enemyIsDead(this);
    }

    /**
     * Permet de gérer le fait que cet objet touche un ennemi.
     */
    @Override
    public void hitEnemy() {
        // Si un ennemi touche un ennemie on ne fais rien
    }

    //Réglage Erreur sonarLint
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    //Réglage Erreur sonarLint
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

