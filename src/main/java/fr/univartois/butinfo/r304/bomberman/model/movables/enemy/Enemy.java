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
    private IMovementStrategy iMovementStrategy; //Instance du patron de conception Strategy

    /**
     * Crée une nouvelle instance de Enemy.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     * @param iMovementStrategy La stratégie de déplacement du personnage ennemi.
     */
    public Enemy(BombermanGame game, double xPosition, double yPosition, Sprite sprite, IMovementStrategy iMovementStrategy) {
        super(game, xPosition, yPosition, sprite);
        this.iMovementStrategy = iMovementStrategy;
    }

    /**
     * Permet de mettre la stratégie de déplacement du personnage ennemi.
     *
     * @param iMovementStrategy La stratégie de déplacement du personnage ennemi.
     */
    public void setDeplacementStrategy(IMovementStrategy iMovementStrategy) {
        this.iMovementStrategy = iMovementStrategy;
    }

    /**
     * Déplace l'ennemi en utilisant la stratégie de déplacement définie.
     *
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    @Override
    public boolean move(long delta) {
        iMovementStrategy.deplacer(this, delta);
        return true;
    }

    /**
     * Déplace l'ennemi en utilisant le déplacement de la classe parente.
     *
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    public boolean superMove(long delta) {
        return super.move(delta);
    }

    /**
     * Gère les collisions de cet objet avec un autre objet.
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
     * Gère l'explosion de cet objet.
     */
    @Override
    public void explode() {
        game.enemyIsDead(this);
    }

    /**
     * Gère le fait que cet objet touche un ennemi.
     */
    @Override
    public void hitEnemy() {
        // Si un ennemi rentre en collision avec un autre ennemi, il ne se passe rien
    }

    /**
     * Indique si l'objet est un ennemi.
     *
     * @return true car cet objet est un ennemi.
     */
    @Override
    public boolean isEnemy() {
        return true;
    }

    /**
     * Indique si l'objet est un joueur.
     *
     * @return false car cet objet n'est pas un joueur.
     */
    @Override
    public boolean isPlayer() {
        return false;
    }

    /**
     * Indique si l'objet est une explosion.
     *
     * @return false car cet objet n'est pas une explosion.
     */
    @Override
    public boolean isExplosion() {
        return false;
    }

    /**
     * Indique si l'objet est un ennemi avec une vie.
     *
     * @return false car cet objet n'est pas un ennemi avec vie.
     */
    @Override
    public boolean isEnemyWithLife() {
        return false;
    }

    /**
     * Indique si l'objet est de la lave.
     *
     * @return false car cet objet n'est pas de la lave.
     */
    @Override
    public boolean isLava() {
        return false;
    }

    /**
     * Indique si l'objet est une bombe.
     *
     * @return false car cet objet n'est pas une bombe.
     */
    @Override
    public boolean isBomb() {
        return false;
    }

    /**
     * Indique si l'objet est une bombe factice.
     *
     * @return false car cet objet n'est pas une bombe factice.
     */
    @Override
    public boolean isFakeBomb() {
        return false;
    }

    /**
     * Indique si l'objet est une grosse bombe.
     *
     * @return false car cet objet n'est pas une grosse bombe.
     */
    @Override
    public boolean isBigBomb() {
        return false;
    }

    /**
     * Indique si l'objet est un bonus invisible.
     *
     * @return false car cet objet n'est pas un bonus invisible.
     */
    @Override
    public boolean isInvisibleBonus() {
        return false;
    }

    /**
     * Indique si l'objet est un bonus de vie.
     *
     * @return false car cet objet n'est pas un bonus de vie.
     */
    @Override
    public boolean isLifeBonus() {
        return false;
    }

    /**
     * Indique si l'objet est un bonus de bombe.
     *
     * @return false car cet objet n'est pas un bonus de bombe.
     */
    @Override
    public boolean isBombBonus() {
        return false;
    }

    /**
     * Compare cet objet avec un autre objet.
     *
     * @param obj L'objet avec lequel comparer cet objet.
     * @return true si les deux objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Récupère le code de hachage de cet objet.
     *
     * @return Le code de hachage de cet objet.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}