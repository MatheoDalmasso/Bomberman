package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.bombs.typebomb.BigBomb;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.IWallState;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * Utility class for bombs
 */
public class BombsUtils {

    /**
     * Private constructor to prevent instantiation
     */
    private BombsUtils() {
        // Utility class
    }

    /**
     * Handle the wall cell
     *
     * @param adjacentCell the adjacent cell
     * @param adjacentX    the adjacent x
     * @param adjacentY    the adjacent y
     * @param spriteStore  the sprite store
     * @param game         the game
     */
    public static void handleWallCell(Cell adjacentCell, int adjacentX, int adjacentY, SpriteStore spriteStore, BombermanGame game) {
        Cell lawnCell = new Cell(spriteStore.getSprite("lawn"));
        Cell cell = game.getCellAt(adjacentX, adjacentY);
        Sprite sp = spriteStore.getSprite("bricks");
        String urlBricks = sp.image().getUrl();
        String urlCrackedBricks = spriteStore.getSprite("cracked-bricks").image().getUrl();
        String urlWall = spriteStore.getSprite("wall").image().getUrl();

        if (isBrickWall(adjacentCell, urlBricks, urlCrackedBricks, urlWall)) {
            IWallState state = adjacentCell.getWall().getState();
            createExplosion(adjacentX, adjacentY, spriteStore, game);
            degradeWall(adjacentCell, state, lawnCell, cell, spriteStore);
        }
    }

    /**
     * Create adjacent explosions bombs
     *
     * @param bomb        the bomb
     * @param spriteStore the sprite store
     * @param game        the game
     * @param directionX  the direction x
     * @param directionY  the direction y
     */
    public static void createAdjacentExplosionsBombs(BigBomb bomb, SpriteStore spriteStore, BombermanGame game, int[] directionX, int[] directionY) {
        for (int i = 0; i < directionX.length; i++) {
            int adjacentX = bomb.getX() + directionX[i] * spriteStore.getSpriteSize();
            int adjacentY = bomb.getY() + directionY[i] * spriteStore.getSpriteSize();

            Cell adjacentCell = game.getCellAt(adjacentX, adjacentY);

            if (adjacentCell != null && adjacentCell.getWall() != null) {
                handleWallCell(adjacentCell, adjacentX, adjacentY, spriteStore, game);
            } else if (adjacentCell != null && adjacentCell.getWall() == null) {
                createExplosion(adjacentX, adjacentY, spriteStore, game);
            }
        }
    }

    /**
     * Check if the adjacent cell is a brick wall
     *
     * @param adjacentCell     the adjacent cell
     * @param urlBricks        the url of the bricks
     * @param urlCrackedBricks the url of the cracked bricks
     * @param urlWall          the url of the wall
     * @return true if the adjacent cell is a brick wall, false otherwise
     */
    private static boolean isBrickWall(Cell adjacentCell, String urlBricks, String urlCrackedBricks, String urlWall) {
        return (adjacentCell.getWall().getSprite().image().getUrl().equals(urlBricks) ||
                adjacentCell.getWall().getSprite().image().getUrl().equals(urlCrackedBricks)) &&
                !adjacentCell.getWall().getSprite().image().getUrl().equals(urlWall);
    }

    /**
     * Create an explosion
     *
     * @param x           the x position
     * @param y           the y position
     * @param spriteStore the sprite store
     * @param game        the game
     */
    private static void createExplosion(int x, int y, SpriteStore spriteStore, BombermanGame game) {
        Explosion explosion = new Explosion(game, x, y, spriteStore.getSprite("explosion"));
        game.addMovable(explosion);
    }

    /**
     * Degrade the wall
     *
     * @param adjacentCell the adjacent cell
     * @param state        the state
     * @param lawnCell     the lawn cell
     * @param cell         the cell
     * @param spriteStore  the sprite store
     */
    private static void degradeWall(Cell adjacentCell, IWallState state, Cell lawnCell, Cell cell, SpriteStore spriteStore) {
        if (state.getSprite().image().getUrl().equals(spriteStore.getSprite("bricks").image().getUrl())) {
            adjacentCell.getWall().degrade();
            IWallState crackedState = adjacentCell.getWall().getState();
            Cell cellWallReplace = new Cell(new Wall(crackedState, adjacentCell.getWall().getPositionX(), adjacentCell.getWall().getPositionY()));
            adjacentCell.replaceBy(cellWallReplace);
        } else {
            adjacentCell.getWall().degrade();
            cell.replaceBy(lawnCell);
        }
    }
}