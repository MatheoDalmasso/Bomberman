/**
 * Classe Enemy : permet de gérer un personnage ennemi.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables.enemy;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movement.IMovementStrategy;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * Classe Enemy : permet de gérer un personnage ennemi.
 */
public class Enemy extends AbstractMovable {

    /**
     * La stratégie de déplacement du personnage ennemi.
     */
    private IMovementStrategy IMovementStrategy; //Instance du patron de conception Strategy

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public Enemy(BombermanGame game, double xPosition, double yPosition, Sprite sprite, IMovementStrategy IMovementStrategy) {
        super(game, xPosition, yPosition, sprite);
        this.IMovementStrategy = IMovementStrategy;
    }

    /**
     * Permet de mettre la stratégie de déplacement du personnage ennemi.
     *
     * @param IMovementStrategy La stratégie de déplacement du personnage ennemi.
     */
    public void setDeplacementStrategy(IMovementStrategy IMovementStrategy) {
        this.IMovementStrategy = IMovementStrategy;
    }

    /**
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *              millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    @Override
    public boolean move(long delta) {
        IMovementStrategy.deplacer(this, delta);
        return true;
    }

    /**
     * Permet de déplacer l'ennemi de manière aléatoire.
     *
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     * @return true si l'objet a bougé, false sinon.
     */
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
        if (other.isEnemy()) {
            hitEnemy();
        }
        if (!(this.isEnemyWithLife())) {
            // Decorateur le fait
        } else {
            if (other.isExplosion()) {
                explode();
            }
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

    @Override
    public boolean isEnemy() {
        return true;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isExplosion() {
        return false;
    }

    @Override
    public boolean isEnemyWithLife() {
        return false;
    }

    @Override
    public boolean isLava() {
        return false;
    }

    @Override
    public boolean isBomb() {
        return false;
    }

    @Override
    public boolean isFakeBomb() {
        return false;
    }

    @Override
    public boolean isBigBomb() {
        return false;
    }

    @Override
    public boolean isInvisibleBonus() {
        return false;
    }

    @Override
    public boolean isLifeBonus() {
        return false;
    }

    @Override
    public boolean isBombBonus() {
        return false;
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

