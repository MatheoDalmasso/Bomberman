/**
 * Classe InvulnerableState : permet de gérer l'état invulnérable du joueur.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables.player.state;

import fr.univartois.butinfo.r304.bomberman.controller.HomeController;
import fr.univartois.butinfo.r304.bomberman.model.movables.player.Player;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Classe InvulnerableState : permet de gérer l'état invulnérable du joueur.
 */
public class InvulnerableState implements PlayerState {

    /**
     * La durée de l'invulnérabilité du joueur.
     */
    private static final int INVULNERABLE_DURATION = 3;

    /**
     * Permet de faire prendre des dégâts au joueur.
     *
     * @param player Le joueur qui prend des dégâts.
     * @param damage Les dégâts à infliger.
     */
    @Override
    public void takeDamage(Player player, int damage) {
        // Le joueur ne peut pas prendre de dégâts
    }

    /**
     * Permet de mettre à jour l'apparence du joueur.
     *
     * @param player Le joueur à mettre à jour.
     */
    @Override
    public void updateAppearance(Player player) {
        player.setSprite(player.getSpriteStore().getSprite("bomberman_1_4"));
    }

    /**
     * Permet de rendre le joueur invulnérable.
     *
     * @param player Le joueur à rendre invulnérable.
     */
    public static void makePlayerInvulnerable(Player player) {
        Sprite originalSprite = player.getSprite();
        player.setState(new InvulnerableState());
        player.setSprite(player.getSpriteStore().getSprite("bomberman_1_4"));
        PauseTransition pause = new PauseTransition(Duration.seconds(INVULNERABLE_DURATION));
        pause.setOnFinished(event -> {
            player.setState(new VulnerableState());
            player.setSprite(originalSprite);
        });
        pause.play();
    }
}
