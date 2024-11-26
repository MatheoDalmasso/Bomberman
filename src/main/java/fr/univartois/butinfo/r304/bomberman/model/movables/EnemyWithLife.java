/**
 * Classe EnemyWithLife : permet de gérer la vie d'un ennemi.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bombe;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;

/**
 * Classe EnemyWithLife : permet de gérer la vie d'un ennemi.
 */
public class EnemyWithLife extends AbstractIMovableDecorator {
    /**
     * L'ennemi à décorer.
     */
    private PersonnageEnnemi ennemi;
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
    public EnemyWithLife(PersonnageEnnemi ennemi, int pv) {
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
        if (other instanceof PersonnageEnnemi) {
            hitEnemy();
        }
        if (other instanceof Explosion && System.currentTimeMillis() - lastHitTime >= 5000) {
            lastHitTime = System.currentTimeMillis();
            decreasePv(1);
            if (pv == 0) {
                ennemi.explode();  // L'ennemi meurt
            }
        }

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
    public void addBomb(Bombe bomb) {
        //
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
