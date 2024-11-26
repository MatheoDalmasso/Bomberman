/**
 * Classe Joueur : permet de gérer le joueur.
 */
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

/**
 * Classe Joueur : permet de gérer le joueur.
 */
public class Joueur extends AbstractMovable {

    /**
     * Le sprite store.
     */
    private SpriteStore spriteStore;

    /**
     * L'état du joueur.
     */
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
    }


    /**
     * Déplace le joueur.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof PersonnageEnnemi || other instanceof EnemyWithLife || other instanceof Explosion) {
            takeDamage(1);
        }
    }

    /**
     * Permet de render invincible le joueur.
     */
    public void makePlayerInvulnerable() {
        InvulnerableState.makePlayerInvulnerable(this);
    }

    /**
     * Permet de recupérer le sprite store.
     *
     * @return Le sprite store.
     */
    public SpriteStore getSpriteStore() {
        return spriteStore;
    }

    /**
     * Permet de recupérer l'état du joueur.
     *
     * @param state L'état du joueur.
     */
    public void setState(PlayerState state) {
        this.state = state;
        state.updateAppearance(this);
    }

    /**
     * Permet d'infliger des dégâts au joueur.
     *
     * @param damage Les dégâts à infliger.
     */
    public void takeDamage(int damage) {
        state.takeDamage(this, damage);
    }

    /**
     * Ajoute une bombe à la liste des bombes.
     *
     * @param bombe La bombe à ajouter.
     */
    public void addBombe(Bombe bombe) {
        bombs.add(bombe);
    }

    @Override
    public void addBomb(Bombe bomb) {
        bombs.add(bomb);
    }

    @Override
    public void addLife(int life) {
        setPointsDeVie(getPointsDeVie() + life);
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
        if (pointsDeVie.get() == 1) {
            game.playerIsDead();
        }
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
