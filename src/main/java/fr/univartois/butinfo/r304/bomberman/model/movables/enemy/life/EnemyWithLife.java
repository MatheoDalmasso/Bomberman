/**
 * Classe EnemyWithLife : permet de gérer la vie d'un ennemi.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables.enemy.life;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bomb;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractIMovableDecorator;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.Enemy;

/**
 * Classe EnemyWithLife : permet de gérer la vie d'un ennemi.
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
     * Permet de gérer les collisions de cet objet avec un autre objet.
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

    @Override
    public boolean isEnemy() {
        return false;
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
        return true;
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
     * Renvoie les PV de l'ennemi.
     *
     * @return Les PV de cet ennemi.
     */
    public int getPv() {
        return pv;
    }

    @Override
    public void addBomb(Bomb bomb) {
        // Fait rien pour les ennemies avec la vie
    }

    @Override
    public void addLife(int life) {
        // Fait rien pour les ennemies avec la vie
    }

    /**
     * Deplacement de l'ennemi
     *
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *              millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    @Override
    public boolean move(long delta) {
        return ennemi.move(delta);
    }
}
