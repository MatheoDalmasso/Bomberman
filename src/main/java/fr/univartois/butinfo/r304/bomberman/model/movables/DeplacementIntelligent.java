package fr.univartois.butinfo.r304.bomberman.model.movables;

public class DeplacementIntelligent implements DeplacementStrategy {
    private Joueur player; // Permet de recup la pos du player

    private static final int TEMPS_MOUVEMENT = 2000;
    private static final double DISTANCE_PIXELS_DU_MOUVEMENT = 36;
    private static final double SPEED = DISTANCE_PIXELS_DU_MOUVEMENT / TEMPS_MOUVEMENT * 1000;

    public DeplacementIntelligent(Joueur player) {
        this.player = player;
    }

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
