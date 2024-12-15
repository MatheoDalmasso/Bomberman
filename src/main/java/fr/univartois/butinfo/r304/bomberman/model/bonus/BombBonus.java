package fr.univartois.butinfo.r304.bomberman.model.bonus;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bomb;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import java.util.Objects;

/**
 * Cette classe représente un bonus de bombe dans le jeu Bomberman.
 */
public class BombBonus extends AbstractMovable implements IWallState {

    /**
     * La bombe associée au bonus.
     */
    private final Bomb bomb;

    /**
     * Le sprite store.
     */
    private final SpriteStore spriteStore = SpriteStore.getInstance();

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public BombBonus(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        bomb = new Bomb(game, xPosition, yPosition, spriteStore.getSprite("bomb"), 4000);
    }

    /**
     * Collisions avec les autres objets.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other.isPlayer()) {
            game.getPlayer().addBomb(bomb);
            game.setRemainingBombs(1);
            game.removeMovable(this);
        }
    }

    /**
     * Gère l'explosion de l'objet.
     */
    @Override
    public void explode() {
        //
    }

    /**
     * Gère le fait que l'explosion a touché un ennemi.
     */
    @Override
    public void hitEnemy() {
        //
    }

    /**
     * Déplace le fait que l'objet est un ennemi.
     *
     * @return true si l'objet est un ennemi, sinon false.
     */
    @Override
    public boolean isEnemy() {
        return false;
    }

    /**
     * Déplace le fait que l'objet est un joueur.
     *
     * @return true si l'objet est un joueur, sinon false.
     */
    @Override
    public boolean isPlayer() {
        return false;
    }

    /**
     * Déplace le fait que l'objet est une explosion.
     *
     * @return true si l'objet est une explosion, sinon false.
     */
    @Override
    public boolean isExplosion() {
        return false;
    }

    /**
     * Ennemi avec vie
     *
     * @return true si l'objet est un ennemi avec vie, sinon false.
     */
    @Override
    public boolean isEnemyWithLife() {
        return false;
    }

    /**
     * C'est une bombe
     *
     * @return true si l'objet est une bombe, sinon false.
     */
    @Override
    public boolean isBomb() {
        return false;
    }

    /**
     * C'est une bombe factice
     *
     * @return true si l'objet est une bombe factice, sinon false.
     */
    @Override
    public boolean isFakeBomb() {
        return false;
    }

    /**
     * C'est une grosse bombe
     *
     * @return true si l'objet est une grosse bombe, sinon false.
     */
    @Override
    public boolean isBigBomb() {
        return false;
    }

    /**
     * C'est un bonus invisible
     *
     * @return true si l'objet est un bonus invisible, sinon false.
     */
    @Override
    public boolean isInvisibleBonus() {
        return false;
    }

    /**
     * C'est un bonus de vie
     *
     * @return true si l'objet est un bonus de vie, sinon false.
     */
    @Override
    public boolean isLifeBonus() {
        return false;
    }

    /**
     * C'est un bonus de bombe
     *
     * @return true si l'objet est un bonus de bombe, sinon false.
     */
    @Override
    public boolean isBombBonus() {
        return true;
    }

    /**
     * Dégrade le mur.
     *
     * @param wall le mur à dégrader
     */
    @Override
    public void degrade(Wall wall) {
        // Ne fais rien après la dégradation
    }

    /**
     * Permet de comparer deux objets.
     *
     * @param o L'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BombBonus bombBonus = (BombBonus) o;
        return Objects.equals(bomb, bombBonus.bomb) && Objects.equals(spriteStore, bombBonus.spriteStore);
    }

    /**
     * Retourne le code de hachage de cet objet.
     *
     * @return Le code de hachage de cet objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bomb, spriteStore);
    }
}
