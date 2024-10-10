package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.Random;

public class PersonnageEnnemi extends AbstractMovable {
    Random random = new Random();
    private int direction; // 0 = gauche, 1 = droite, 2 = haut, 3 = bas
    private long debutMouvement;

    private static final int TEMPS_MOUVEMENT = 2000; // Durée de mouvement en millisecondes
    private static final double DISTANCE_PIXELS_DU_MOUVEMENT = 36; // Distance de mouvement en pixels
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
     *
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *        millisecondes).
     *
     * @return
     */
    @Override
    public boolean move(long delta) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - debutMouvement > TEMPS_MOUVEMENT) {
            changeDirection();
            debutMouvement = currentTime;
        }

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
        }

        boolean moved = super.move((long) SPEED);

        if(!moved){
            changeDirection();
        }
        setHorizontalSpeed(0);
        setVerticalSpeed(0);

        return moved;

    }

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
        if(other instanceof Explosion){
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
        // Si un ennemi touche un ennemie on ne fais rien
    }

    //Réglage Erreur sonarLint
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    //Réglage Erreur sonarLint
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

