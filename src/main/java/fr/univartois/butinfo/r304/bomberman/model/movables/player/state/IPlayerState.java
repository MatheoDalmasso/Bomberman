package fr.univartois.butinfo.r304.bomberman.model.movables.player.state;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.player.Joueur;

public interface IPlayerState {

    void collideWith(Joueur player, IMovable other);

    void explode(Joueur player);

}
