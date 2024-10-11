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

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public PersonnageEnnemi(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        changeDirection();
        debutMouvement = System.currentTimeMillis();
    }

    /**
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *              millisecondes).
     * @return true si l'objet a bougé, false sinon.
     */
    @Override
    public boolean move(long delta) {
        //Nous avons décidé d'adapter les mouvements des ennemies avec des mouvements aléatoires avec un mouvement toutes les 2 secondes

        long currentTime = System.currentTimeMillis(); // Temps actuel en millisecondes

        if (currentTime - debutMouvement > TEMPS_MOUVEMENT) { // Si le temps de mouvement est écoulé
            changeDirection(); // On change de direction
            debutMouvement = currentTime; // On met à jour le temps de début de mouvement
        }

        // On déplace l'objet dans la direction actuelle
        switch (direction) {
            case 0: //cas vers la gauche
                setHorizontalSpeed(-SPEED);
                break;
            case 1: //cas vers la droite
                setHorizontalSpeed(SPEED);
                break;
            case 2: //cas vers le bas
                setVerticalSpeed(-SPEED);
                break;
            case 3: //cas vers le haut
                setVerticalSpeed(SPEED);
                break;
            default: //cas par défaut
                break;
        }

        // On récupère la valeur de retour de la méthode move de la classe mère
        boolean moved = super.move(delta);

        // Si l'objet n'a pas bougé, on change de direction
        if (!moved) {
            changeDirection();
        }

        setHorizontalSpeed(0);
        setVerticalSpeed(0);

        return moved;
    }

    /**
     * Change la direction de cet objet.
     */
    private void changeDirection() {
        direction = random.nextInt(4);
        setHorizontalSpeed(0);
        setVerticalSpeed(0);
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

