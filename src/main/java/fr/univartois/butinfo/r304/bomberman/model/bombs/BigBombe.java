package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class BigBombe extends AbstractMovable implements IBombe {

    private long delai;
    private SpriteStore spriteStore = new SpriteStore();
    private long startTime = -1;


    public BigBombe(BombermanGame game, double xPosition, double yPosition, Sprite sprite, long delai) {
        super(game, xPosition, yPosition, sprite);
        this.delai = delai;
    }

    @Override
    public void poseBombe() {
        if (game.getRemainingBombs() > 0) {
            delai = System.currentTimeMillis();
        }
    }

    @Override
    public boolean move(long delta) {
        if (startTime == -1) {
            startTime = System.currentTimeMillis();
            poseBombe();
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= 5000) {
            new Explosion(game, xPosition.get(), yPosition.get(), spriteStore.getSprite("explosion"));
            new Explosion(game, xPosition.get() + spriteStore.getSpriteSize(), yPosition.get(), spriteStore.getSprite("explosion"));
            new Explosion(game, xPosition.get() + spriteStore.getSpriteSize() * 2, yPosition.get(), spriteStore.getSprite("explosion"));
            new Explosion(game, xPosition.get() - spriteStore.getSpriteSize(), yPosition.get(), spriteStore.getSprite("explosion"));
            new Explosion(game, xPosition.get() - spriteStore.getSpriteSize() * 2, yPosition.get(), spriteStore.getSprite("explosion"));
            new Explosion(game, xPosition.get(), yPosition.get() + spriteStore.getSpriteSize(), spriteStore.getSprite("explosion"));
            new Explosion(game, xPosition.get(), yPosition.get() + spriteStore.getSpriteSize() * 2, spriteStore.getSprite("explosion"));
            new Explosion(game, xPosition.get(), yPosition.get() - spriteStore.getSpriteSize(), spriteStore.getSprite("explosion"));
            new Explosion(game, xPosition.get(), yPosition.get() - spriteStore.getSpriteSize() * 2, spriteStore.getSprite("explosion"));
            game.removeMovable(this);
            game.getCellAt((int)xPosition.get(), (int)yPosition.get()).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            game.getCellAt((int)xPosition.get() + spriteStore.getSpriteSize(), (int)yPosition.get()).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            game.getCellAt((int)xPosition.get() + spriteStore.getSpriteSize() * 2, (int)yPosition.get()).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            game.getCellAt((int)xPosition.get() - spriteStore.getSpriteSize(), (int)yPosition.get()).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            game.getCellAt((int)xPosition.get() - spriteStore.getSpriteSize() * 2, (int)yPosition.get()).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            game.getCellAt((int)xPosition.get(), (int)yPosition.get() + spriteStore.getSpriteSize()).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            game.getCellAt((int)xPosition.get(), (int)yPosition.get() + spriteStore.getSpriteSize() * 2).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            game.getCellAt((int)xPosition.get(), (int)yPosition.get() - spriteStore.getSpriteSize()).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            game.getCellAt((int)xPosition.get(), (int)yPosition.get() - spriteStore.getSpriteSize() * 2).replaceBy(new Cell(spriteStore.getSprite("lawn")));
            return true;
        }
        return false;
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof IBombe) {
            explode();
        }
    }

    @Override
    public void explode() {
        hitEnemy();
    }

    @Override
    public void hitEnemy() {
        game.removeMovable(this);
    }
}
