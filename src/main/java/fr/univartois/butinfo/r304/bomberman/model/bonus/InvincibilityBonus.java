package fr.univartois.butinfo.r304.bomberman.model.bonus;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class InvincibilityBonus extends AbstractMovable implements IWallState {


    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public InvincibilityBonus(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other.isPlayer()) {
            game.getPlayer().makePlayerInvulnerable(); // Méthode déjà dans la classe
            game.removeMovable(this);
        }
    }

    @Override
    public void explode() {
        //
    }

    @Override
    public void hitEnemy() {
        //
    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isExplosion() {
        return false;
    }

    @Override
    public boolean isEnemyWithLife() {
        return false;
    }

    @Override
    public boolean isLava() {
        return false;
    }

    @Override
    public boolean isBomb() {
        return false;
    }

    @Override
    public boolean isFakeBomb() {
        return false;
    }

    @Override
    public boolean isBigBomb() {
        return false;
    }

    @Override
    public boolean isInvisibleBonus() {
        return true;
    }

    @Override
    public boolean isLifeBonus() {
        return false;
    }

    @Override
    public boolean isBombBonus() {
        return false;
    }

    @Override
    public void degrade(Wall wall) {
        //
    }
}
