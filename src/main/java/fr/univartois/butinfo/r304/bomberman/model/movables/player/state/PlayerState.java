/**
 * Package PlayerState
 */
package fr.univartois.butinfo.r304.bomberman.model.movables.player.state;

import fr.univartois.butinfo.r304.bomberman.model.movables.player.Joueur;

/**
 * Interface PlayerState : permet de gérer l'état du joueur
 */
public interface PlayerState {

    /**
     * Permet de faire prendre des dégâts au joueur
     *
     * @param player Le joueur qui prend des dégâts
     * @param damage Les dégâts à infliger
     */
    void takeDamage(Joueur player, int damage);

    /**
     * Permet de mettre à jour l'apparence du
     *
     * @param player Le joueur à mettre à jour
     */
    void updateAppearance(Joueur player);
}
