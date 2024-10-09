package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bombe;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Joueur extends AbstractMovable {

    private IntegerProperty score;

    private IntegerProperty pointsDeVie;

    private IntegerProperty nbBombe;

    private ObservableList<Bombe> bombs;

    /**
     * Crée une nouvelle instance de AbstractMovable.
     * @param game
     * @param xPosition
     * @param yPosition
     * @param sprite
     */
    public Joueur(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.score = new SimpleIntegerProperty(0);
        this.pointsDeVie = new SimpleIntegerProperty(3);
        this.nbBombe = new SimpleIntegerProperty(1);
        this.bombs = FXCollections.observableArrayList();
    }

    /**
     * Déplace le joueur.
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof PersonnageEnnemi || other instanceof Explosion) {
            decrementPointsDeVie(1);
        }
    }

    /**
     * Ajoute une bombe à la liste des bombes.
     * @param bombe
     */
    public void addBombe(Bombe bombe) {
        bombs.add(bombe);
    }

    /**
     * Supprime une bombe de la liste des bombes.
     * @return
     */
    public ObservableList<Bombe> getBombs() {
        return bombs;
    }


    @Override
    public void explode() {
        decrementPointsDeVie(1);
    }

    /**
     * Handles the explosion event for the player.
     * Decreases the player's life points by 1.
     */
    @Override
    public void hitEnemy() {
        decrementPointsDeVie(1);
    }


    /**
     * Returns the score property of the player.
     *
     * @return The score property.
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Returns the life points property of the player.
     *
     * @return The life points property.
     */
    public IntegerProperty pointsDeVieProperty() {
        return pointsDeVie;
    }

    /**
     * Returns the bomb count property of the player.
     *
     * @return The bomb count property.
     */
    public IntegerProperty nbBombeProperty() {
        return nbBombe;
    }

    /**
     * Returns the number of bombs the player has.
     *
     * @return The number of bombs.
     */
    public int getNbBombe() {
        return nbBombe.get();
    }

    /**
     * Returns the score of the player.
     *
     * @return The score.
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Sets the score of the player.
     *
     * @param score The new score.
     */
    public void setScore(int score) {
        this.score.set(score);
    }

    /**
     * Returns the life points of the player.
     *
     * @return The life points.
     */
    public int getPointsDeVie() {
        return pointsDeVie.get();
    }

    /**
     * Sets the life points of the player.
     *
     * @param pointsDeVie The new life points.
     */
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie.set(pointsDeVie);
    }

    /**
     * Increments the score of the player by a specified amount.
     *
     * @param score The amount to increment the score by.
     */
    public void incrementScore(int score) {
        this.score.set(this.score.get() + score);
    }

    /**
     * Decrements the life points of the player by a specified amount.
     *
     * @param pointsDeVie The amount to decrement the life points by.
     */
    public void decrementPointsDeVie(int pointsDeVie) {
        this.pointsDeVie.set(this.pointsDeVie.get() - pointsDeVie);
    }

    /**
     * Checks if this player is equal to another object.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Returns the hash code of this player.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
