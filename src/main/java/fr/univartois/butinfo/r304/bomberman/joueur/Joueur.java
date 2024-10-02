package fr.univartois.butinfo.r304.bomberman.joueur;

import com.sun.javafx.image.IntPixelGetter;
import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur extends AbstractMovable {

    private IntegerProperty score;

    private IntegerProperty pointsDeVie;


    public Joueur(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.score = new SimpleIntegerProperty(0);
        this.pointsDeVie = new SimpleIntegerProperty(3);
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

    public IntegerProperty scoreProperty() {
        return score;
    }

    public IntegerProperty pointsDeVieProperty() {
        return pointsDeVie;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public int getPointsDeVie() {
        return pointsDeVie.get();
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie.set(pointsDeVie);
    }

    public void incrementScore(int score){
        this.score.set(this.score.get() + score);
    }

    public void decrementPointsDeVie(int pointsDeVie){
        this.pointsDeVie.set(this.pointsDeVie.get() - pointsDeVie);
    }
}
