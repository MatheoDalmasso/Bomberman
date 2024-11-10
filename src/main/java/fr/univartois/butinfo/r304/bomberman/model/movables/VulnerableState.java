/**
 * Classe qui gère l'état vulnérable du joueur
 */
package fr.univartois.butinfo.r304.bomberman.model.movables;

/**
 * Classe qui gère l'état vulnérable du joueur
 */
public class VulnerableState implements PlayerState {

    /**
     * Permet de faire prendre des dégâts au joueur
     *
     * @param player Le joueur qui prend des dégâts
     * @param damage Les dégâts à infliger
     */
    @Override
    public void takeDamage(Joueur player, int damage) {
        player.decrementPointsDeVie(damage);
        if (player.getPointsDeVie() <= 0) {
            player.game.playerIsDead();
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
    public void updateAppearance(Joueur player) {
        player.setSprite(player.getSpriteStore().getSprite("agent"));
    }
}
