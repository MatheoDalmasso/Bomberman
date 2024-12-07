package fr.univartois.butinfo.r304.bomberman.model.movables.enemy.life;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bomb;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractIMovableDecorator;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.Enemy;

/**
 * La classe EnemyWithLife permet de gérer la vie d'un ennemi dans le jeu Bomberman.
 * Elle étend la classe AbstractIMovableDecorator pour ajouter des fonctionnalités de gestion de vie à un ennemi.
 */
public class EnemyWithLife extends AbstractIMovableDecorator {
    /**
     * L'ennemi à décorer.
     */
    private final Enemy ennemi;
    /**
     * Les points de vie de l'ennemi.
     */
    private int pv;

    /**
     * Le moment où l'ennemi a été touché pour la dernière fois.
     */
    private long lastHitTime = 0;

    /**
     * Crée une nouvelle instance de EnemyWithLife.
     *
     * @param ennemi L'ennemi à décorer.
     * @param pv     Les points de vie de l'ennemi.
     */
    public EnemyWithLife(Enemy ennemi, int pv) {
        super(ennemi);
        this.ennemi = ennemi;
        this.pv = pv;
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
        if (other.isExplosion() && System.currentTimeMillis() - lastHitTime >= 5000) {
            lastHitTime = System.currentTimeMillis();
            decreasePv(1);
            if (pv == 0) {
                ennemi.explode();  // L'ennemi meurt
            }
        }
    }

    /**
     * Indique si l'objet est un ennemi.
     *
     * @return false car cet objet est un ennemi avec vie.
     */
    @Override
    public boolean isEnemy() {
        return false;
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
     * @return true car cet objet est un ennemi avec vie.
     */
    @Override
    public boolean isEnemyWithLife() {
        return true;
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
     * Diminue les points de vie de l'ennemi.
     *
     * @param amount La quantité de PV à diminuer.
     */
    @Override
    public void decreasePv(int amount) {
        pv -= amount;
        if (pv <= 0) {
            pv = 0;
        }
    }

    /**
     * Ajoute une bombe à l'ennemi.
     *
     * @param bomb La bombe à ajouter.
     */
    @Override
    public void addBomb(Bomb bomb) {
        // Ne fait rien pour les ennemis avec vie
    }

    /**
     * Ajoute des points de vie à l'ennemi.
     *
     * @param life La quantité de points de vie à ajouter.
     */
    @Override
    public void addLife(int life) {
        // Ne fait rien pour les ennemis avec vie
    }

    /**
     * Déplace l'ennemi.
     *
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    @Override
    public boolean move(long delta) {
        return ennemi.move(delta);
    }
}