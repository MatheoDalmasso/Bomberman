package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;

public interface IPlayerState {

    void collideWith(Joueur player , IMovable other);
    void explode(Joueur player);

}
