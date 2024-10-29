package fr.univartois.butinfo.r304.bomberman.model.movables;

public class DeplacementIntelligent implements DeplacementStrategy {
    private Joueur player;

    public DeplacementIntelligent(Joueur player) {
        this.player = player;
    }

    @Override
    public void deplacer(PersonnageEnnemi ennemi, long delta) {
        double xPlayer = player.getX();
        double yPlayer = player.getY();

        if (ennemi.getX() < xPlayer) {
            ennemi.setHorizontalSpeed(ennemi.getHorizontalSpeed());
        } else if (ennemi.getX() > xPlayer) {
            ennemi.setHorizontalSpeed(-ennemi.getHorizontalSpeed());
        }

        if (ennemi.getY() < yPlayer) {
            ennemi.setVerticalSpeed(ennemi.getVerticalSpeed());
        } else if (ennemi.getY() > yPlayer) {
            ennemi.setVerticalSpeed(-ennemi.getVerticalSpeed());
        }

        ennemi.move(delta);

        ennemi.setHorizontalSpeed(0);
        ennemi.setVerticalSpeed(0);
    }
}
