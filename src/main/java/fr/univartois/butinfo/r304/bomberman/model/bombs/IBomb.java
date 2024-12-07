package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;

public interface IBomb {
    /**
     * Decrémente le nombre de bombes disponibles.
     */
    void poseBombe();

    /**
     * Déplace l'objet de delta.
     *
     * @param delta Le déplacement à effectuer.
     * @return Vrai si le déplacement a été effectué, faux sinon.
     */
    boolean move(long delta);

    /**
     * Gère la collision avec un autre objet.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    void collidedWith(IMovable other);

    /**
     * Gère l'explosion de l'objet.
     */
    void explode();

    /**
     * Gère le fait que l'explosion a touché un ennemi.
     */
    void hitEnemy();

}
