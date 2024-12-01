package fr.univartois.butinfo.r304.bomberman.model.movables.player.state;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.player.Player;

public interface IPlayerState {

    void collideWith(Player player, IMovable other);

    void explode(Player player);

}
