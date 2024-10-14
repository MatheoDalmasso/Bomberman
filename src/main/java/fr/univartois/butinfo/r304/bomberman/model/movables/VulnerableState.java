package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;

public class VulnerableState implements IPlayerState {

    @Override
    public void collideWith(Joueur player, IMovable other) {
        if (other instanceof PersonnageEnnemi || other instanceof Explosion) {
            player.decrementPointsDeVie(1);
        }
    }

    @Override
    public void explode(Joueur joueur) {
        joueur.decrementPointsDeVie(1);
    }
}
