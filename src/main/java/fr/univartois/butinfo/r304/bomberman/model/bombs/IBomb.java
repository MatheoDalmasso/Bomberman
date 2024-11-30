package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;

public interface IBomb {
    void poseBombe();

    boolean move(long delta);

    void collidedWith(IMovable other);

    void explode();

    void hitEnemy();
}
