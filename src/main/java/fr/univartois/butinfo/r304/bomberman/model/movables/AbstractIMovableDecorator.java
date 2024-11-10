package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;

public abstract class AbstractIMovableDecorator implements IMovable {

    protected IMovable iMovable;

    protected AbstractIMovableDecorator(IMovable iMovable) {
        this.iMovable = iMovable;
    }


    public DoubleProperty getXProperty() {
        return iMovable.getXProperty();
    }


    public DoubleProperty getYProperty() {
        return iMovable.getYProperty();
    }


    public void consume() {
        iMovable.consume();
    }


    public boolean isConsumed() {
        return iMovable.isConsumed();
    }


    public void setHorizontalSpeed(double speed) {
        iMovable.setHorizontalSpeed(speed);
    }


    public double getHorizontalSpeed() {
        return iMovable.getHorizontalSpeed();
    }


    public void setVerticalSpeed(double speed) {
        iMovable.setVerticalSpeed(speed);
    }


    public double getVerticalSpeed() {
        return iMovable.getVerticalSpeed();
    }


    public void setSprite(Sprite sprite) {
        iMovable.setSprite(sprite);
    }


    public Sprite getSprite() {
        return iMovable.getSprite();
    }


    public boolean move(long timeDelta) {
        return iMovable.move(timeDelta);
    }


    public boolean isCollidingWith(IMovable other) {
        return iMovable.isCollidingWith(other);
    }


    public void explode() {
        iMovable.explode();
    }


    public void hitEnemy() {
        iMovable.hitEnemy();
    }


    public int getWidth() {
        return iMovable.getWidth();
    }


    public int getHeight() {
        return iMovable.getHeight();
    }

    public int getY() {
        return iMovable.getY();
    }

    public int getX() {
        return iMovable.getX();
    }

    public void setY(int yPosition) {
        iMovable.setY(yPosition);
    }

    public void setX(int xPosition) {
        iMovable.setX(xPosition);
    }

    @Override
    public ObjectProperty<Sprite> getSpriteProperty() {
        return iMovable.getSpriteProperty();
    }

    @Override
    public BooleanProperty isConsumedProperty() {
        return iMovable.isConsumedProperty();
    }

    @Override
    public IMovable self() {
        return iMovable;
    }

    public abstract int getPv();

    public abstract void decreasePv(int amount);
}
