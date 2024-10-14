package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
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
        if (elapsedTime >= 4000) {
            detonateBomb();
            return true;
        }
        return false;
    }

    /**
     * Fait exploser la bombe
     */
    private void detonateBomb() {
        Cell bombCell = game.getCellAt(getX(), getY());
        if (bombCell.getWall() == null) {
            createExplosion(getX(), getY());
        }
        createAdjacentExplosions();
        game.removeMovable(this);
        game.decreaseBombs();
    }

    /**
     * Crée une explosion
     *
     * @param x La position x
     * @param y La position y
     */
    private void createExplosion(int x, int y) {
        Explosion explosion = new Explosion(game, x, y, spriteStore.getSprite("explosion"));
        game.addMovable(explosion);
    }

    /**
     * Crée des explosions adjacentes
     */
    private void createAdjacentExplosions() {
        // Définir les directions (haut, bas, gauche, droite)
        int[] directionX = {0, 0, -1, 1, -2, 2};
        int[] directionY = {-2, 2, -1, 1, 0, 0};

        for (int i = 0; i < directionX.length; i++) {
            int adjacentX = getX() + directionX[i] * spriteStore.getSpriteSize();
            int adjacentY = getY() + directionY[i] * spriteStore.getSpriteSize();

            Cell adjacentCell = game.getCellAt(adjacentX, adjacentY);

            if (adjacentCell.getWall() == null) {
                createExplosion(adjacentX, adjacentY);
            }
        }
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
