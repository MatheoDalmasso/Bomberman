/**
 * Package contenant les classes du modèle représentant la carte du jeu.
 */
package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.GetGameInstance;
import fr.univartois.butinfo.r304.bomberman.model.bonus.RecupBombe;
import fr.univartois.butinfo.r304.bomberman.model.bonus.RecupInvincibilite;
import fr.univartois.butinfo.r304.bomberman.model.bonus.RecupVie;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import java.util.Random;

/**
 * Classe représentant l'état d'un mur en brique fissuré.
 */
public class CrackedBrickWallState implements IWallState {
    private final Sprite sprite;
    private BombermanGame game = GetGameInstance.getInstance();
    private SpriteStore spriteStore = SpriteStore.getInstance();
    private Random random = new Random();

    /**
     * Crée un nouvel état de mur en brique fissuré.
     *
     * @param sprite Le sprite représentant le mur en brique fissuré.
     */
    public CrackedBrickWallState(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Dégrade le mur.
     *
     * @param wall le mur à dégrader
     */
    @Override
    public void degrade(Wall wall) {
        int chance = random.nextInt(100);
        if (chance < 40) {
            RecupBombe recupBombe = new RecupBombe(game, wall.getPositionX(), wall.getPositionY(), spriteStore.getSprite("bombPlus"));
            game.addMovable(recupBombe);
            wall.setState(recupBombe);
        } else if (chance < 55) {
            RecupVie recupVie = new RecupVie(game, wall.getPositionX(), wall.getPositionY(), spriteStore.getSprite("heartPlus"));
            game.addMovable(recupVie);
            wall.setState(recupVie);
        } else if (chance < 60) {
            RecupInvincibilite recupInvincibilite = new RecupInvincibilite(game, wall.getPositionX(), wall.getPositionY(), spriteStore.getSprite("Invincibility-stars"));
            game.addMovable(recupInvincibilite);
            wall.setState(recupInvincibilite);
        }
    }

    /**
     * Récupère le sprite associé à l'état du mur.
     *
     * @return le sprite associé à l'état du mur
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
