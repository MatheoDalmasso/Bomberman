package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bombe;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

public class Joueur extends AbstractMovable {

    private SpriteStore spriteStore;

    private PlayerState state;

    /**
     * score du joueur.
     */
    private IntegerProperty score;

    /**
     * Points de vie du joueur.
     */
    private IntegerProperty pointsDeVie;

    /**
     * Nombre de bombes du joueur.
     */
    private IntegerProperty nbBombe;

    /**
     * Liste des bombes du joueur.
     */
    private ObservableList<Bombe> bombs;

    private IPlayerState state;

    private final IPlayerState vulnerableState;

    private final IPlayerState invulnerableState;

    private SpriteStore spriteStore = new SpriteStore();

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public Joueur(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.spriteStore = new SpriteStore();
        this.state = new VulnerableState();
        this.score = new SimpleIntegerProperty(0);
        this.pointsDeVie = new SimpleIntegerProperty(3);
        this.nbBombe = new SimpleIntegerProperty(1);
        this.bombs = FXCollections.observableArrayList();
        this.vulnerableState = new VulnerableState();
        this.invulnerableState = new InvulnerableState();
        this.state = vulnerableState;
    }


    /**
     * Déplace le joueur.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof PersonnageEnnemi || other instanceof Explosion) {
            takeDamage(1);
        }
    }

    public void makePlayerInvulnerable() {
        InvulnerableState.makePlayerInvulnerable(this);
    }

    public SpriteStore getSpriteStore() {
        return spriteStore;
    }


    public void setState(PlayerState state) {
        this.state = state;
        state.updateAppearance(this);
    }

    public void takeDamage(int damage) {
        state.takeDamage(this , damage);
    }

    /**
     * Ajoute une bombe à la liste des bombes.
     *
     * @param bombe La bombe à ajouter.
     */
    public void addBombe(Bombe bombe) {
        bombs.add(bombe);
    }

    /**
     * Supprime une bombe de la liste des bombes.
     *
     * @return le nombre de bombes restantes.
     */
    public ObservableList<Bombe> getBombs() {
        return bombs;
    }

    /**
     * Décremente les points de vie du joueur quand il explose.
     */
    @Override
    public void explode() {
        takeDamage(1);
    }

    /**
     * Décremente la vie du joueur quand il est touché par un ennemi.
     */
    @Override
    public void hitEnemy() {
        takeDamage(1);
    }


    /**
     * Retourne le score du joueur.
     *
     * @return Le score.
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Retourne les points de vie du joueur.
     *
     * @return Les points de vie.
     */
    public IntegerProperty pointsDeVieProperty() {
        return pointsDeVie;
    }

    /**
     * Retourne le nombre de bombes du joueur.
     *
     * @return Le nombre de bombes.
     */
    public IntegerProperty nbBombeProperty() {
        return nbBombe;
    }

    /**
     * Retourne le nombre de bombes du joueur.
     *
     * @return Le nombre de bombes.
     */
    public int getNbBombe() {
        return nbBombe.get();
    }

    /**
     * Retourne le score du joueur.
     *
     * @return Le score.
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Change le score du joueur.
     *
     * @param score Le nouveau score.
     */
    public void setScore(int score) {
        this.score.set(score);
    }

    /**
     * Retourne les points de vie du joueur.
     *
     * @return Les points de vie.
     */
    public int getPointsDeVie() {
        return pointsDeVie.get();
    }

    /**
     * Change les points de vie du joueur.
     *
     * @param pointsDeVie Les nouveaux points de vies.
     */
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie.set(pointsDeVie);
    }

    public void setState(IPlayerState state) {
        this.state = state;
    }

    /**
     * Incrémente le score du joueur par un montant précis.
     *
     * @param score Le montant à ajouter au score.
     */
    public void incrementScore(int score) {
        this.score.set(this.score.get() + score);
    }

    /**
     * Decrémente le nombre de points de vies du joueur par un montant précis.
     *
     * @param pointsDeVie Le montant à retirer aux points de vie.
     */
    public void decrementPointsDeVie(int pointsDeVie) {
        this.pointsDeVie.set(this.pointsDeVie.get() - pointsDeVie);
    }

    /**
     * Check si le joueur est égale à un autre objet.
     *
     * @param obj L'ojet à comparer.
     * @return Vraie si les objets sont égaux, faux sinon.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Retourne le hash code de l'objet.
     *
     * @return Le hash code.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
