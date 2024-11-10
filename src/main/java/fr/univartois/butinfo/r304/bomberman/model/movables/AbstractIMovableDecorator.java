/**
 * Le code ci-dessus est un exemple de classe qui implémente l'interface IMovable.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;

/**
 * Le code ci-dessus est un exemple de classe qui implémente l'interface IMovable.
 */
public abstract class AbstractIMovableDecorator implements IMovable {

    /**
     * Le IMovable à décorer
     */
    protected IMovable iMovable;

    /**
     * Le constructeur de la classe AbstractIMovableDecorator
     *
     * @param iMovable Le IMovable à décorer
     */
    protected AbstractIMovableDecorator(IMovable iMovable) {
        this.iMovable = iMovable;
    }

    /**
     * Renvoie la X position de l'IMovable
     *
     * @return La X position de l'IMovable
     */
    public DoubleProperty getXProperty() {
        return iMovable.getXProperty();
    }


    /**
     * Revoie la Y position de l'IMovable
     *
     * @return La Y position de l'IMovable
     */
    public DoubleProperty getYProperty() {
        return iMovable.getYProperty();
    }

    /**
     * Consume l'IMovable
     */
    public void consume() {
        iMovable.consume();
    }

    /**
     * Verifie si l'IMovable est consommé
     *
     * @return Vrai si l'IMovable est consommé, faux sinon
     */
    public boolean isConsumed() {
        return iMovable.isConsumed();
    }

    /**
     * Change la speed horizontale de l'objet en pixels/s
     *
     * @param speed La nouvelle vitesse horizontale de cet objet (en pixels/s).
     */
    public void setHorizontalSpeed(double speed) {
        iMovable.setHorizontalSpeed(speed);
    }

    /**
     * Renvoie la speed horizontale de l'objet en pixels/s
     *
     * @return La vitesse horizontale de cet objet (en pixels/s).
     */
    public double getHorizontalSpeed() {
        return iMovable.getHorizontalSpeed();
    }


    /**
     * Change la speed verticale de l'objet en pixels/s
     *
     * @param speed La nouvelle vitesse verticale de cet objet (en pixels/s).
     */
    public void setVerticalSpeed(double speed) {
        iMovable.setVerticalSpeed(speed);
    }

    /**
     * Renvoie la speed verticale de l'objet en pixels/s
     *
     * @return La vitesse verticale de cet objet (en pixels/s).
     */
    public double getVerticalSpeed() {
        return iMovable.getVerticalSpeed();
    }

    /**
     * Change la sprite de l'objet
     *
     * @param sprite La nouvelle instance de {@link Sprite} représentant cet objet.
     */
    public void setSprite(Sprite sprite) {
        iMovable.setSprite(sprite);
    }

    /**
     * Renvoie la sprite de l'objet
     *
     * @return La sprite de cet objet.
     */
    public Sprite getSprite() {
        return iMovable.getSprite();
    }

    /**
     * Déplace l'objet
     *
     * @param timeDelta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *                  millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    public boolean move(long timeDelta) {
        return iMovable.move(timeDelta);
    }

    /**
     * Vérifie si l'objet est en collision avec un autre objet
     *
     * @param other L'objet avec lequel la collision doit être vérifiée.
     * @return true si l'objet est en collision avec l'autre objet, false sinon.
     */
    public boolean isCollidingWith(IMovable other) {
        return iMovable.isCollidingWith(other);
    }

    /**
     * Si l'objet est en collision avec un autre objet il explose
     */
    public void explode() {
        iMovable.explode();
    }

    /**
     * Si l'objet est en collision avec un ennemi
     */
    public void hitEnemy() {
        iMovable.hitEnemy();
    }

    /**
     * Renvoie la largeur de l'objet
     *
     * @return La largeur de cet objet.
     */
    public int getWidth() {
        return iMovable.getWidth();
    }

    /**
     * Renvoie la hauteur de l'objet
     *
     * @return La hauteur de cet objet.
     */
    public int getHeight() {
        return iMovable.getHeight();
    }

    /**
     * Renvoie la position Y de l'objet
     *
     * @return La position Y de cet objet.
     */
    public int getY() {
        return iMovable.getY();
    }

    /**
     * Renvoie la position X de l'objet
     *
     * @return La position X de cet objet.
     */
    public int getX() {
        return iMovable.getX();
    }

    /**
     * Change la position en y de l'objet
     *
     * @param yPosition La nouvelle position en y de cet objet.
     */
    public void setY(int yPosition) {
        iMovable.setY(yPosition);
    }

    /**
     * Change la position en x de l'objet
     *
     * @param xPosition La nouvelle position en x de cet objet.
     */
    public void setX(int xPosition) {
        iMovable.setX(xPosition);
    }

    /**
     * Renvoie le sprite de l'objet
     *
     * @return Le sprite de cet objet.
     */
    @Override
    public ObjectProperty<Sprite> getSpriteProperty() {
        return iMovable.getSpriteProperty();
    }

    /**
     * Renvoie si l'objet est consommé
     *
     * @return Vrai si l'objet est consommé, faux sinon
     */
    @Override
    public BooleanProperty isConsumedProperty() {
        return iMovable.isConsumedProperty();
    }

    /**
     * Renvoie l'instance de l'IMovable décoré.
     *
     * @return L'instance de l'IMovable décoré.
     */
    @Override
    public IMovable self() {
        return iMovable;
    }

    /**
     * Renvoie les PV de l'objet
     *
     * @return Les PV de cet objet.
     */
    public abstract int getPv();

    /**
     * Diminue les PV de l'objet
     *
     * @param amount La quantité de PV à diminuer.
     */
    public abstract void decreasePv(int amount);
}
