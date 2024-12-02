package fr.univartois.butinfo.r304.bomberman.model.bonus;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bomb;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.beans.property.IntegerProperty;

import java.util.Objects;

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
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other.isPlayer()) {
            game.getPlayer().addBomb(bomb);
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
     * @return
     */
    @Override
    public boolean isEnemy() {
        return false;
    }

    /**
     * Déplace le fait que l'objet est un joueur.
     * @return
     */
    @Override
    public boolean isPlayer() {
        return false;
    }

    /**
     * Déplace le fait que l'objet est une explosion.
     * @return
     */
    @Override
    public boolean isExplosion() {
        return false;
    }

    /**
     * Ennemi avec vie
     * @return
     */
    @Override
    public boolean isEnemyWithLife() {
        return false;
    }

    /**
     * C'est de la lave
     * @return
     */
    @Override
    public boolean isLava() {
        return false;
    }

    /**
     * C'est une bombe
     * @return
     */
    @Override
    public boolean isBomb() {
        return false;
    }

    /**
     * C'est une bombe factice
     * @return
     */
    @Override
    public boolean isFakeBomb() {
        return false;
    }

    /**
     * C'est une grosse bombe
     * @return
     */
    @Override
    public boolean isBigBomb() {
        return false;
    }

    /**
     * C'est un bonus invisible
     * @return
     */
    @Override
    public boolean isInvisibleBonus() {
        return false;
    }

    /**
     * C'est un bonus de vie
     * @return
     */
    @Override
    public boolean isLifeBonus() {
        return false;
    }

    /**
     * C'est un bonus de bombe
     * @return
     */
    @Override
    public boolean isBombBonus() {
        return true;
    }

    /**
     * Dégrade le mur.
     * @param wall le mur à dégrader
     */
    @Override
    public void degrade(Wall wall) {
        // Ne fais rien après la dégradation
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BombBonus bombBonus = (BombBonus) o;
        return Objects.equals(bomb, bombBonus.bomb) && Objects.equals(spriteStore, bombBonus.spriteStore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bomb, spriteStore);
    }
}
