package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;

public class EnemyWithLife extends AbstractIMovableDecorator {
    private PersonnageEnnemi ennemi;
    private int pv;

    public EnemyWithLife(PersonnageEnnemi ennemi, int pv) {
        super(ennemi);
        this.ennemi = ennemi;
        this.pv = pv;
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Explosion) {
            System.out.println("Explosion touchée !");
            if (pv == 0) {
                ennemi.explode();  // L'ennemi meurt
            } else {
                decreasePv(1);  // Réduit les PV de 1
                System.out.println("PV restants: " + pv);
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
