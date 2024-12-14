/**
 * La classe BigBomb représente une bombe de grande taille.
 */
package fr.univartois.butinfo.r304.bomberman.model.bombs.typebomb;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bomb;
import fr.univartois.butinfo.r304.bomberman.model.bombs.BombsUtils;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Explosion;
import fr.univartois.butinfo.r304.bomberman.model.bombs.IBomb;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import java.util.Objects;

/**
 * La classe BigBomb représente une bombe de grande taille.
 */
public class BigBomb extends AbstractMovable implements IBomb {

    /**
     * Le délai avant l'explosion
     */
    private long delai;

    /**
     * Le SpriteStore
     */
    private final SpriteStore spriteStore = SpriteStore.getInstance();

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
    public BigBomb(BombermanGame game, double xPosition, double yPosition, Sprite sprite, long delai) {
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
    public void detonateBomb() {
        Cell bombCell = game.getCellAt(getX(), getY());
        if (bombCell.getWall() == null) {
            createExplosion(getX(), getY());
        }
        createAdjacentExplosions();
        game.removeMovable(this);
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

        BombsUtils.createAdjacentExplosionsBombs(this, spriteStore, game, directionX, directionY);
    }

    /**
     * Si l'objet est en collision avec un autre objet il explose
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other.isExplosion()) {
            // Si la bombe entre en collision avec une explosion, elle explose également
            if (startTime == -1) {
                startTime = System.currentTimeMillis(); // On définit le temps de début
            }
            detonateBomb(); // Déclenche l'explosion de cette bombe
        } else if (other.isBomb()){
            // Si une bombe ou une grosse bombe entre en collision avec une autre bombe
            Bomb otherBomb = (Bomb) other;  // Cast de l'objet en Bomb
            if (otherBomb.getStartTime() == -1) {  // Si la deuxième bombe n'a pas encore explosé
                otherBomb.setStartTime();  // On définit son temps de début
                otherBomb.detonateBomb();  // Déclenche l'explosion de la seconde bombe
            }
        }
        else if(other.isBigBomb()) {
            BigBomb otherBigBomb = (BigBomb) other;
            if (otherBigBomb.startTime == -1) {  // Si la deuxième
                otherBigBomb.startTime = System.currentTimeMillis();  // On définit son temps de début
                otherBigBomb.detonateBomb();
            }
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
    public boolean isBomb() {
        return false;
    }

    @Override
    public boolean isFakeBomb() {
        return false;
    }

    @Override
    public boolean isBigBomb() {
        return true;
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
        BigBomb bigBombe = (BigBomb) o;
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

    public long getStartTime() {
        return startTime;
    }
    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }
}
