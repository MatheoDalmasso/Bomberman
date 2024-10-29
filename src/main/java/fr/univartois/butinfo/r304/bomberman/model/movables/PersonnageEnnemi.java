package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class PersonnageEnnemi extends AbstractMovable {

    private DeplacementStrategy deplacementStrategy; //Instance du patron de conception Strategy

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public PersonnageEnnemi(BombermanGame game, double xPosition, double yPosition, Sprite sprite, DeplacementStrategy deplacementStrategy) {
        super(game, xPosition, yPosition, sprite);
        this.deplacementStrategy = deplacementStrategy;
    }

    public void setDeplacementStrategy(DeplacementStrategy deplacementStrategy) {
        this.deplacementStrategy = deplacementStrategy;
    }

    /**
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *              millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    @Override
    public boolean move(long delta) {
        deplacementStrategy.deplacer(this, delta);
        return true;
    }

    public boolean superMove(long delta) {
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
        }
        if (other instanceof Explosion) {
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
        // Si un ennemi rentre en collision avec un autre ennemi, il ne se passe rien
    }

    /**
     * Permet de comparer cet objet avec un autre objet.
     *
     * @param obj L'objet avec lequel comparer cet objet.
     * @return true si les deux objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Permet de récupérer le code de hachage de cet objet.
     *
     * @return Le code de hachage de cet objet.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

