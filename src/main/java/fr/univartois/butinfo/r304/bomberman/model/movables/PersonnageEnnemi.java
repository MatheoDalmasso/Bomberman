package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.Random;

public class PersonnageEnnemi extends AbstractMovable {
    Random random = new Random();
    private int direction; // 0 = gauche, 1 = droite, 2 = bas, 3 = haut
    private long debutMouvement;

    private static final int TEMPS_MOUVEMENT = 2000; // Durée de mouvement en millisecondes du mouvement à réaliser
    private static final double DISTANCE_PIXELS_DU_MOUVEMENT = 36; // Distance de mouvement en pixels du mouvement à réaliser
    private static final double SPEED = DISTANCE_PIXELS_DU_MOUVEMENT / TEMPS_MOUVEMENT * 1000; // Vitesse de déplacement en pixels par seconde

    private DeplacementStrategy deplacementStrategy; //Instance du patron de conception Strategy

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public PersonnageEnnemi(BombermanGame game, double xPosition, double yPosition, Sprite sprite, DeplacementStrategy deplacementStrategy) {
        super(game, xPosition, yPosition, sprite);
        this.deplacementStrategy = deplacementStrategy;
    }

    public void setDeplacementStrategy(DeplacementStrategy deplacementStrategy) {
        this.deplacementStrategy = deplacementStrategy;
    }

    /**
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *              millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    @Override
    public boolean move(long delta) {
        deplacementStrategy.deplacer(this, delta);
        return true;
    }

    public boolean superMove(long delta) {
        return super.move(delta);
    }

    /**
     * Permet de gérer les collisions de cet objet avec un autre objet.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof PersonnageEnnemi) {
            hitEnemy();
        }
        if (other instanceof Explosion) {
            explode();
        }
    }

    /**
     * Permet de gérer l'explosion de cet objet.
     */
    @Override
    public void explode() {
        game.enemyIsDead(this);
    }

    /**
     * Permet de gérer le fait que cet objet touche un ennemi.
     */
    @Override
    public void hitEnemy() {
        // Si un ennemi rentre en collision avec un autre ennemi, il ne se passe rien
    }

    /**
     * Permet de comparer cet objet avec un autre objet.
     *
     * @param obj L'objet avec lequel comparer cet objet.
     * @return true si les deux objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Permet de récupérer le code de hachage de cet objet.
     *
     * @return Le code de hachage de cet objet.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

