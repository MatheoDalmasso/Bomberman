package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Bombe extends AbstractMovable {
    private static final Logger LOGGER = LogManager.getLogManager().getLogger(Bombe.class.getPackageName());
    private long delai;
    private SpriteStore spriteStore = new SpriteStore();
    private GameMap map = game.getGameMap();
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

    public void poseBombe() {
        if (game.getRemainingBombs() > 0) {
            delai = System.currentTimeMillis();
            game.decreaseBombs();
        } else {
            LOGGER.info("Pas de bombes disponibles.");
        }
    }

    @Override
    public boolean move(long delta) {
        // Récupérer la position de la bombe
        int bombX = getX();
        int bombY = getY();

        Cell bombCell = game.getCellAt(bombX, bombY);

        if (startTime == -1) {
            startTime = System.currentTimeMillis();
            poseBombe();
        }

        long elapsedTime = System.currentTimeMillis() - startTime;

        if (elapsedTime < 2500) {

            if (bombCell.getWall() == null) {
                Explosion explosion = new Explosion(game, bombX, bombY, spriteStore.getSprite("explosion"));
                game.addMovable(explosion);
            }

            int[][] directions = {
                    {0, -1}, // haut
                    {0, 1},  // bas
                    {-1, 0}, // gauche
                    {1, 0}    // droite
            };

            for (int i = 0; i < directions.length; i++) {
                for (int j = 0; j < directions[i].length; j++) {
                    if (j == 0) { // Si c'est la direction en x
                        int adjacentX = bombX + directions[i][j] * spriteStore.getSpriteSize();
                        int adjacentY = bombY + directions[i][1] * spriteStore.getSpriteSize();

                        Cell adjacentCell = game.getCellAt(adjacentX, adjacentY);
                        if (adjacentCell.getWall() == null) {
                            Explosion adjExplosion = new Explosion(game, adjacentX, adjacentY, spriteStore.getSprite("explosion"));
                            game.addMovable(adjExplosion);
                        }
                    }
                }

            }
            game.removeMovable(this);
            return true;
        }
        return false;
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Explosion) {
            this.explode();
        }
    }

    @Override
    public void explode() {
        game.removeMovable(this);
    }

    @Override
    public void hitEnemy() {
        LOGGER.info("La bombe a touché un ennemi");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
