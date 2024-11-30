/**
 * Classe qui gère l'état vulnérable du joueur
 */
package fr.univartois.butinfo.r304.bomberman.model.movables.player.state;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.GetGameInstance;
import fr.univartois.butinfo.r304.bomberman.model.movables.player.Player;

/**
 * Classe qui gère l'état vulnérable du joueur
 */
public class VulnerableState implements PlayerState {

    private final BombermanGame game = GetGameInstance.getInstance();

    /**
     * Permet de faire prendre des dégâts au joueur
     *
     * @param player Le joueur qui prend des dégâts
     * @param damage Les dégâts à infliger
     */
    @Override
    public void takeDamage(Player player, int damage) {
        player.decrementPointsDeVie(damage);
        if (player.getPointsDeVie() <= 0) {
            game.playerIsDead();
        } else {
            player.makePlayerInvulnerable();
        }
    }

    /**
     * Permet de mettre à jour l'apparence du joueur
     *
     * @param player Le joueur à mettre à jour
     */
    @Override
    public void updateAppearance(Player player) {
        player.setSprite(player.getSpriteStore().getSprite("agent"));
    }
}
