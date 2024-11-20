/**
 * La classe BigBombe représente une bombe de grande taille.
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

import java.util.Objects;

/**
 * La classe BigBombe représente une bombe de grande taille.
 */
public class BigBombe extends AbstractMovable implements IBombe {

    /**
     * Le délai avant l'explosion
     */
    private long delai;

    /**
     * Le SpriteStore
     */
    private SpriteStore spriteStore = SpriteStore.getInstance();

    /**
     * Le temps de début
     */
    private long startTime = -1;

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     * @param delai     Le délai avant l'explosion
     */
    public BigBombe(BombermanGame game, double xPosition, double yPosition, Sprite sprite, long delai) {
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
     * Pose la bombe et la fait explosée
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
     * Si l'objet est en collision avec un autre objet il explose
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof IBombe) {
            explode();
        }
    }

    /**
     * Si l'objet est en collision avec un autre objet il explose
     */
    @Override
    public void explode() {
        hitEnemy();
    }

    /**
     * Si l'objet est en collision avec un ennemi
     */
    @Override
    public void hitEnemy() {
        game.removeMovable(this);
    }

    /**
     * Renvoi si un objet et egale a un autre
     *
     * @param o L'objet avec lequel cet objet doit être comparé.
     * @return true si l'objet est égal à l'autre objet, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BigBombe bigBombe = (BigBombe) o;
        return delai == bigBombe.delai && startTime == bigBombe.startTime && Objects.equals(spriteStore, bigBombe.spriteStore);
    }

    /**
     * Renvoi le hashcode de l'objet
     *
     * @return Le code de hachage de cet objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), delai, spriteStore, startTime);
    }
}
