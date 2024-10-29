package fr.univartois.butinfo.r304.bomberman.model.movables;

import java.util.Random;

public class DeplacementAleatoire implements DeplacementStrategy {
    Random random = new Random();
    private int direction;
    private long debutMouvement;

    private static final int TEMPS_MOUVEMENT = 2000;
    private static final double DISTANCE_PIXELS_DU_MOUVEMENT = 36;
    private static final double SPEED = DISTANCE_PIXELS_DU_MOUVEMENT / TEMPS_MOUVEMENT * 1000;

    @Override
    public void deplacer(PersonnageEnnemi ennemi, long delta) {

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

        boolean moved = ennemi.move(delta);

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
