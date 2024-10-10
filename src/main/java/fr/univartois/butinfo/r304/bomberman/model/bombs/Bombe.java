package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.PersonnageEnnemi;
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
        delai = System.currentTimeMillis();
    }

    @Override
    public boolean move(long delta) {
        if (startTime == -1) {
            startTime = System.currentTimeMillis();
            poseBombe();
        }

        long elapsedTime = System.currentTimeMillis() - startTime;

        if (elapsedTime < 2500) {
            // Display bomb and explosion during the 4 seconds
            map.setAt(this.getY(), this.getX(), new Cell(spriteStore.getSprite("bomb")));
            for (double i = -1; i < 2; i++) {
                for (double j = -1; j < 2; j++) {
                    if (!(i == 0 && j == 0)) {
                        if (map.getAt((int) (this.getY() + i), (int) (this.getX() + j)).getWall() == null) {
                            Explosion explosion = new Explosion(game, this.getX() + j, this.getY() + i, spriteStore.getSprite("explosion"));
                            map.setAt((int) (this.getY() + i), (int) (this.getX() + j), new Cell(spriteStore.getSprite("explosion")));
                            game.addMovable(explosion);
                        }
                    }


                }
            }
        } else {
            System.out.println("test");
            // After 4 seconds, display lawn
            for (double i = -1; i < 2; i++) {
                for (double j = -1; j < 2; j++) {
                    if (map.getAt((int) (this.getY() + i), (int) (this.getX() + j)).getSprite().equals(spriteStore.getSprite("explosion"))) {
                        System.out.println("Test3");
                        map.setAt((int) (this.getY() + i), (int) (this.getX() + j), new Cell(spriteStore.getSprite("lawn")));
                    }
                }
            }
            this.game.removeMovable(this);
            return true;
        }
        System.out.println("test2");
        return false;
    }

    @Override
    public void collidedWith(IMovable other) {
        if (other instanceof Explosion) {
            this.explode();
        }
        if (other instanceof PersonnageEnnemi) {
            this.hitEnemy();
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
