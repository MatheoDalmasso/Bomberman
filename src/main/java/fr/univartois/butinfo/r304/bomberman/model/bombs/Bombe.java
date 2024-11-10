/**
 * Cette classe représente une bombe dans le jeu Bomberman.
 */
package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.BrickWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Cette classe représente une bombe dans le jeu Bomberman.
 */
public class Bombe extends AbstractMovable implements IBombe {
    private static final Logger LOGGER = LogManager.getLogManager().getLogger(Bombe.class.getPackage().getName());
    private long delai;
    private SpriteStore spriteStore = new SpriteStore();
    private long startTime = -1;


    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public Bombe(BombermanGame game, double xPosition, double yPosition, Sprite sprite, long delai) {
        super(game, xPosition, yPosition, sprite);
        this.delai = delai;
    }

    /**
     * Decrémente le nombre de bombes disponibles.
     */
    @Override
    public void poseBombe() {
        if (game.getRemainingBombs() > 0) {
            delai = System.currentTimeMillis();
        }
    }

    /**
     * Enlève la bombe de la carte.
     *
     * @param delta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *              millisecondes).
     * @return true si la bombe a explosé, false sinon.
     */
    @Override
    public boolean move(long delta) {
        if (startTime == -1) {
            startTime = System.currentTimeMillis();
            poseBombe();
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= 2500) {
            detonateBomb();
            return true;
        }
        return false;
    }

    /**
     * Fait exploser la bombe
     */
    private void detonateBomb() {
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
        int[] directionX = {0, 0, -1, 1};
        int[] directionY = {-1, 1, 0, 0};

        for (int i = 0; i < directionX.length; i++) {
            int adjacentX = getX() + directionX[i] * spriteStore.getSpriteSize();
            int adjacentY = getY() + directionY[i] * spriteStore.getSpriteSize();

            Cell adjacentCell = game.getCellAt(adjacentX, adjacentY);

            if (adjacentCell != null && adjacentCell.getWall() != null) {
                handleWallCell(adjacentCell, adjacentX, adjacentY);
            } else if (adjacentCell != null && adjacentCell.getWall() == null) {
                createExplosion(adjacentX, adjacentY);
            }
        }
    }

    /**
     * Gère les collisions avec les murs
     *
     * @param adjacentCell la cellule adjacente
     * @param adjacentX    la position x de la cellule adjacente
     * @param adjacentY    la position y de la cellule adjacente
     */
    private void handleWallCell(Cell adjacentCell, int adjacentX, int adjacentY) {
        Cell lawnCell = new Cell(spriteStore.getSprite("lawn"));
        Cell cell = game.getCellAt(adjacentX, adjacentY);
        Sprite sp = spriteStore.getSprite("bricks");
        String urlBricks = sp.getImage().getUrl();
        String urlCrackedBricks = spriteStore.getSprite("cracked-bricks").getImage().getUrl();
        String urlWall = spriteStore.getSprite("wall").getImage().getUrl();

        if (isBrickWall(adjacentCell, urlBricks, urlCrackedBricks, urlWall)) {
            IWallState state = adjacentCell.getWall().getState();
            createExplosion(adjacentX, adjacentY);
            degradeWall(adjacentCell, state, lawnCell, cell);
        }
    }

    /**
     * Vérifie si la cellule est un mur en brique
     *
     * @param adjacentCell     la cellule adjacente
     * @param urlBricks        l'url des briques
     * @param urlCrackedBricks l'url des briques fissurées
     * @param urlWall          l'url du mur
     * @return true si la cellule est un mur en brique, false sinon
     */
    private boolean isBrickWall(Cell adjacentCell, String urlBricks, String urlCrackedBricks, String urlWall) {
        return (adjacentCell.getWall().getSprite().getImage().getUrl().equals(urlBricks) ||
                adjacentCell.getWall().getSprite().getImage().getUrl().equals(urlCrackedBricks)) &&
                !adjacentCell.getWall().getSprite().getImage().getUrl().equals(urlWall);
    }

    /**
     * Dégrade le mur
     *
     * @param adjacentCell les cases adjacentes
     * @param state        l'état du mur
     * @param lawnCell     la cellule de l'herbe
     * @param cell         la cellule
     */
    private void degradeWall(Cell adjacentCell, IWallState state, Cell lawnCell, Cell cell) {
        if (state instanceof BrickWallState) {
            adjacentCell.getWall().degrade();
            IWallState crackedState = adjacentCell.getWall().getState();
            Cell cellWallReplace = new Cell(new Wall(crackedState));
            adjacentCell.replaceBy(cellWallReplace);
        } else {
            adjacentCell.getWall().degrade();
            cell.replaceBy(lawnCell);
        }
    }

    /**
     * Gère les collisions entre cet objet et un autre objet.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Explosion) {
            this.explode();
        }
    }

    /**
     * Gère l'explosion de cet objet.
     */
    @Override
    public void explode() {
        game.removeMovable(this);
    }

    /**
     * Gère le fait que cette explosion a touché un ennemi.
     */
    @Override
    public void hitEnemy() {
        LOGGER.info("La bombe a touché un ennemi");
    }

    /**
     * Retourne le code de hachage de cet objet.
     *
     * @return Le code de hachage de cet objet.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Vérifie si cet objet est égal à un autre objet.
     *
     * @param obj L'objet à comparer.
     * @return Si cet objet est égal à l'autre objet.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Donne le délai avant l'explosion de la bombe.
     *
     * @return Le délai avant l'explosion.
     */
    public long getDelai() {
        return delai;
    }

    /**
     * Modifie le délai
     *
     * @param delai Le nouveau délai.
     */
    public void setDelai(long delai) {
        this.delai = delai;
    }
}
