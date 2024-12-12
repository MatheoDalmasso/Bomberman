/**
 * Cette classe représente une bombe dans le jeu Bomberman.
 */
package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.typebomb.BigBomb;
import fr.univartois.butinfo.r304.bomberman.model.bombs.typebomb.FakeBomb;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Cette classe représente une bombe dans le jeu Bomberman.
 */
public class Bomb extends AbstractMovable implements IBomb {
    /**
     * Le logger de la classe.
     */
    private static final Logger LOGGER = LogManager.getLogManager().getLogger(Bomb.class.getPackage().getName());
    /**
     * Le délai avant l'exp
     * losion de la bombe.
     */
    private long delai;
    /**
     * Le sprite store.
     */
    private final SpriteStore spriteStore = SpriteStore.getInstance();
    /**
     * Le temps de début de la bombe.
     */
    private long startTime = -1;


    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public Bomb(BombermanGame game, double xPosition, double yPosition, Sprite sprite, long delai) {
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
            startTime = System.currentTimeMillis();  // Initialise le départ si non défini
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime >= delai) {  // Si le délai est atteint, on fait exploser la bombe
            detonateBomb();
            return true;
        }
        return false;
    }

    /**
     * Fait exploser la bombe
     */
    public void detonateBomb() {
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
        BombsUtils.handleWallCell(adjacentCell, adjacentX, adjacentY, spriteStore, game);
    }


    /**
     * Gère les collisions entre cet objet et un autre objet.
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
        } else if (other.isBomb()) {
            // Si une bombe ou une grosse bombe entre en collision avec une autre bombe
            Bomb otherBomb = (Bomb) other;  // Cast de l'objet en Bomb
            if (otherBomb.startTime == -1) {  // Si la deuxième bombe n'a pas encore explosé
                otherBomb.startTime = System.currentTimeMillis();  // On définit son temps de début
                otherBomb.detonateBomb();  // Déclenche l'explosion de la seconde bombe
            }
        }
        else if(other.isBigBomb()) {
            BigBomb otherBigBomb = (BigBomb) other;
            if (otherBigBomb.getStartTime() == -1) {  // Si la deuxième
                otherBigBomb.setStartTime();  // On définit son temps de début
                otherBigBomb.detonateBomb();
            }
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
     * Gère le fait que cette explosion a touché un joueur.
     */
    @Override
    public boolean isEnemy() {
        return false;
    }

    /**
     * Gère le fait que cette explosion a touché un joueur.
     */
    @Override
    public boolean isPlayer() {
        return false;
    }

    /**
     * Gère le fait que cette
     * explosion a touché un joueur.
     */
    @Override
    public boolean isExplosion() {
        return false;
    }

    /**
     * Ennemi avec vie
     *
     * @return false
     */
    @Override
    public boolean isEnemyWithLife() {
        return false;
    }

    /**
     * C'est une bombe
     *
     * @return true
     */
    @Override
    public boolean isBomb() {
        return true;
    }

    /**
     * C'est une fausse bombe
     *
     * @return false
     */
    @Override
    public boolean isFakeBomb() {
        return false;
    }

    /**
     * C'est une grosse bombe
     *
     * @return false
     */
    @Override
    public boolean isBigBomb() {
        return false;
    }

    /**
     * C'est un bonus invincible
     *
     * @return false
     */
    @Override
    public boolean isInvisibleBonus() {
        return false;
    }

    /**
     * C'est un bonus de vie
     *
     * @return false
     */
    @Override
    public boolean isLifeBonus() {
        return false;
    }

    /**
     * C'est un bonus de bombe
     *
     * @return false
     */
    @Override
    public boolean isBombBonus() {
        return false;
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
     * @param obj L'objet a comparé.
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

    public long getStartTime() {
        return startTime;
    }
    public void setStartTime() {
        this.startTime=System.currentTimeMillis();
    }
}
