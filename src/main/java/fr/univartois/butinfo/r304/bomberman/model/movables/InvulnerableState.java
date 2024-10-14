package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;

public class InvulnerableState implements IPlayerState {


    @Override
    public void collideWith(Joueur player, IMovable other) {
        // Pas d'effet lors de l'invulnérabilité
    }

    @Override
    public void explode(Joueur joueur) {
        // Pas d'effet lors de l'invulnérabilité
    }
}