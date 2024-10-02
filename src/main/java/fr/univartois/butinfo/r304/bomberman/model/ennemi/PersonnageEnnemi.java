package fr.univartois.butinfo.r304.bomberman.model.ennemi;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;

public class PersonnageEnnemi extends AbstractMovable implements IMovable {
    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    protected PersonnageEnnemi(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setX(int xPosition) {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public DoubleProperty getXProperty() {
        return null;
    }

    @Override
    public void setY(int yPosition) {

    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public DoubleProperty getYProperty() {
        return null;
    }

    @Override
    public void consume() {

    }

    @Override
    public boolean isConsumed() {
        return false;
    }

    @Override
    public BooleanProperty isConsumedProperty() {
        return null;
    }

    @Override
    public void setHorizontalSpeed(double speed) {

    }

    @Override
    public double getHorizontalSpeed() {
        return 0;
    }

    @Override
    public void setVerticalSpeed(double speed) {

    }

    @Override
    public double getVerticalSpeed() {
        return 0;
    }

    @Override
    public void setSprite(Sprite sprite) {

    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    @Override
    public ObjectProperty<Sprite> getSpriteProperty() {
        return null;
    }

    @Override
    public boolean move(long timeDelta) {
        return false;
    }

    @Override
    public boolean isCollidingWith(IMovable other) {
        return false;
    }

    @Override
    public void collidedWith(IMovable other) {

    }

    @Override
    public void explode() {

    }

    @Override
    public void hitEnemy() {

    }

    @Override
    public IMovable self() {
        return null;
    }
}
