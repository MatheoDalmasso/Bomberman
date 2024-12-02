/**
 * Classe RandomMovement : permet de déplacer un personnage ennemi de manière aléatoire.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movement;

import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.Enemy;

import java.security.SecureRandom;

/**
 * Classe RandomMovement : permet de déplacer un personnage ennemi de manière aléatoire.
 */
public class RandomMovement implements IMovementStrategy {
    /**
     * Le générateur de nombres aléatoires.
     */
    private final SecureRandom random = new SecureRandom();

    /**
     * La direction actuelle de cet objet.
     */
    private int direction;

    /**
     * Le moment où le mouvement a commencé.
     */
    private long debutMouvement;

    /**
     * Temps de mouvement d'un ennemi.
     */
    private static final int TEMPS_MOUVEMENT = 2000;

    /**
     * La distance en pixels du mouvement.
     */
    private static final double DISTANCE_PIXELS_DU_MOUVEMENT = 36; // Distance en pixels du mouvement

    /**
     * La vitesse de déplacement.
     */
    private static final double SPEED = DISTANCE_PIXELS_DU_MOUVEMENT / TEMPS_MOUVEMENT * 1000; // La speed

    /**
     * Permet de déplacer l'ennemi de manière aléatoire.
     *
     * @param ennemi L'ennemi à déplacer.
     * @param delta  Le temps écoulé depuis le dernier déplacement de cet objet (en
     */
    @Override
    public void deplacer(Enemy ennemi, long delta) {

        long currentTime = System.currentTimeMillis();

        if (currentTime - debutMouvement > TEMPS_MOUVEMENT) {
            changeDirection();
            debutMouvement = currentTime;
        }

        switch (direction) {
            case 0: //cas vers la gauche
                ennemi.setHorizontalSpeed(-SPEED);
                break;
            case 1: //cas vers la droite
                ennemi.setHorizontalSpeed(SPEED);
                break;
            case 2: //cas vers le bas
                ennemi.setVerticalSpeed(-SPEED);
                break;
            case 3: //cas vers le haut
                ennemi.setVerticalSpeed(SPEED);
                break;
            default: //cas par défaut
                break;
        }

        boolean moved = ennemi.superMove(delta);

        if (!moved) {
            changeDirection();
        }

        ennemi.setHorizontalSpeed(0);
        ennemi.setVerticalSpeed(0);
    }

    /**
     * Change la direction de cet objet.
     */
    private void changeDirection() {
        direction = random.nextInt(4);
    }
}
