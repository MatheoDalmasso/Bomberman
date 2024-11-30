/**
 * Classe DeplacementIntelligent : permet de déplacer un personnage ennemi de manière intelligente.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables.enemy.deplacement;

import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.PersonnageEnnemi;
import fr.univartois.butinfo.r304.bomberman.model.movables.player.Joueur;

/**
 * Classe DeplacementIntelligent : permet de déplacer un personnage ennemi de manière intelligente.
 */
public class DeplacementIntelligent implements DeplacementStrategy {
    /**
     * Le joueur pour lequel l'ennemi doit se déplacer.
     */
    private Joueur player; // Permet de recup la pos du player

    /**
     * Temps de mouvement d'un ennemi.
     */
    private static final int TEMPS_MOUVEMENT = 2000;

    /**
     * La distance en pixels du mouvement.
     */
    private static final double DISTANCE_PIXELS_DU_MOUVEMENT = 36;

    /**
     * La vitesse de déplacement.
     */
    private static final double SPEED = DISTANCE_PIXELS_DU_MOUVEMENT / TEMPS_MOUVEMENT * 1000;

    /**
     * Crée une nouvelle instance de DeplacementIntelligent.
     *
     * @param player Le joueur pour lequel l'ennemi doit se déplacer.
     */
    public DeplacementIntelligent(Joueur player) {
        this.player = player;
    }

    /**
     * Permet de déplacer l'ennemi de manière intelligente.
     *
     * @param ennemi L'ennemi à déplacer.
     * @param delta  Le temps écoulé depuis le dernier déplacement de cet objet (en
     */
    @Override
    public void deplacer(PersonnageEnnemi ennemi, long delta) {
        double xPlayer = player.getX();
        double yPlayer = player.getY();

        if (ennemi.getX() < xPlayer) {
            ennemi.setHorizontalSpeed(SPEED);
        } else if (ennemi.getX() > xPlayer) {
            ennemi.setHorizontalSpeed(-SPEED);
        }

        if (ennemi.getY() < yPlayer) {
            ennemi.setVerticalSpeed(SPEED);
        } else if (ennemi.getY() > yPlayer) {
            ennemi.setVerticalSpeed(-SPEED);
        }

        boolean moved = ennemi.superMove(delta);


        if (!moved) {
            // Tente de contourner le wall
            if (ennemi.getHorizontalSpeed() != 0) { // Horizontal (mur droite ou gauche) alors
                ennemi.setHorizontalSpeed(0);
                ennemi.setVerticalSpeed(SPEED); // On descend
                if (!ennemi.superMove(delta)) {
                    ennemi.setVerticalSpeed(-SPEED); // Sinon on monte
                    ennemi.superMove(delta);
                }
            } else if (ennemi.getVerticalSpeed() != 0) { // Vertical (mur haut ou bas) alors
                ennemi.setVerticalSpeed(0);
                ennemi.setHorizontalSpeed(SPEED); // On va a droite
                if (!ennemi.superMove(delta)) {
                    ennemi.setHorizontalSpeed(-SPEED); // Sinon gauche
                    ennemi.superMove(delta);
                }

            }
        }
    }
}
