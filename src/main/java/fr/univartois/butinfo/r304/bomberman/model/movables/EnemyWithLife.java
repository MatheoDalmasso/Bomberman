package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;

public class EnemyWithLife extends AbstractIMovableDecorator {
    private PersonnageEnnemi ennemi;
    private int pv;
    private long lastHitTime = 0;

    public EnemyWithLife(PersonnageEnnemi ennemi, int pv) {
        super(ennemi);
        this.ennemi = ennemi;
        this.pv = pv;
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof PersonnageEnnemi) {
            hitEnemy();
        }
        if (other instanceof Explosion) {
            if (System.currentTimeMillis() - lastHitTime >= 5000) {
                lastHitTime = System.currentTimeMillis();
                decreasePv(1);
                if (pv == 0) {
                    ennemi.explode();  // L'ennemi meurt
                } else {
                    // Réduit les PV de 1
                    System.out.println("PV restants: " + pv);
                }
            }
        }
    }

    @Override
    public void decreasePv(int amount) {
        pv -= amount;
        if (pv <= 0) {
            pv = 0;
        }
    }

    public int getPv() {
        return pv;
    }

    @Override
    public boolean move(long delta) {
        return ennemi.move(delta);
    }
}
