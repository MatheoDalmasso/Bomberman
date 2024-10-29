package fr.univartois.butinfo.r304.bomberman.model.movables;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class InvulnerableState implements PlayerState {

    private static final int INVULNERABLE_DURATION = 3;

    @Override
    public void takeDamage(Joueur player, int damage) {
        // Le joueur ne peut pas prendre de dégâts
    }

    @Override
    public void updateAppearance(Joueur player) {
        player.setSprite(player.getSpriteStore().getSprite("punker"));
    }

    public static void makePlayerInvulnerable(Joueur player) {
        player.setState(new InvulnerableState());
        player.setSprite(player.getSpriteStore().getSprite("punker"));
        PauseTransition pause = new PauseTransition(Duration.seconds(INVULNERABLE_DURATION));
        pause.setOnFinished(event -> {
            player.setState(new VulnerableState());
            player.setSprite(player.getSpriteStore().getSprite("agent"));
        });
        pause.play();
    }
}
