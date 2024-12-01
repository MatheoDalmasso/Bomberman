package fr.univartois.butinfo.r304.bomberman.model.map;


import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.player.Player;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class Lava extends AbstractMovable implements IWallState {

    private final Sprite sprite;

    public Lava(Sprite sprite, BombermanGame game, double xPosition, double yPosition) {
        super(game, xPosition, yPosition, sprite);
        this.sprite = sprite;
    }

    @Override
    public void degrade(Wall wall) {
        // Do nothing
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Player player) {
            player.takeDamage(1);
        }
    }

    @Override
    public void explode() {
        // Do nothing
    }

    @Override
    public void hitEnemy() {
        // Do nothing
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
        return true;
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
        return false;
    }

    @Override
    public boolean isLifeBonus() {
        return false;
    }

    @Override
    public boolean isBombBonus() {
        return false;
    }

}
