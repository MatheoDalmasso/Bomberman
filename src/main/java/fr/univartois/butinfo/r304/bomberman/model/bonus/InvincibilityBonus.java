package fr.univartois.butinfo.r304.bomberman.model.bonus;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstate.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * La classe InvincibilityBonus représente un bonus d'invincibilité dans le jeu Bomberman.
 * Lorsqu'un joueur entre en collision avec ce bonus, il devient invulnérable pendant une certaine période.
 */
public class InvincibilityBonus extends AbstractMovable implements IWallState {

    /**
     * Crée une nouvelle instance de InvincibilityBonus.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public InvincibilityBonus(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }

    /**
     * Gère la collision avec un autre objet.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        if (other.isPlayer()) {
            game.getPlayer().makePlayerInvulnerable(); // Méthode déjà dans la classe
            game.removeMovable(this);
        }
    }

    /**
     * Gère l'explosion de l'objet.
     */
    @Override
    public void explode() {
        // Implémentation vide car ce bonus ne réagit pas aux explosions
    }

    /**
     * Gère le fait que l'explosion a touché un ennemi.
     */
    @Override
    public void hitEnemy() {
        // Implémentation vide car ce bonus ne réagit pas aux ennemis
    }

    /**
     * Indique si l'objet est un ennemi.
     *
     * @return false car ce bonus n'est pas un ennemi.
     */
    @Override
    public boolean isEnemy() {
        return false;
    }

    /**
     * Indique si l'objet est un joueur.
     *
     * @return false car ce bonus n'est pas un joueur.
     */
    @Override
    public boolean isPlayer() {
        return false;
    }

    /**
     * Indique si l'objet est une explosion.
     *
     * @return false car ce bonus n'est pas une explosion.
     */
    @Override
    public boolean isExplosion() {
        return false;
    }

    /**
     * Indique si l'objet est un ennemi avec une vie.
     *
     * @return false car ce bonus n'est pas un ennemi avec une vie.
     */
    @Override
    public boolean isEnemyWithLife() {
        return false;
    }

    /**
     * Indique si l'objet est une bombe.
     *
     * @return false car ce bonus n'est pas une bombe.
     */
    @Override
    public boolean isBomb() {
        return false;
    }

    /**
     * Indique si l'objet est une bombe factice.
     *
     * @return false car ce bonus n'est pas une bombe factice.
     */
    @Override
    public boolean isFakeBomb() {
        return false;
    }

    /**
     * Indique si l'objet est une grosse bombe.
     *
     * @return false car ce bonus n'est pas une grosse bombe.
     */
    @Override
    public boolean isBigBomb() {
        return false;
    }

    /**
     * Indique si l'objet est un bonus invisible.
     *
     * @return true car ce bonus est un bonus invisible.
     */
    @Override
    public boolean isInvisibleBonus() {
        return true;
    }

    /**
     * Indique si l'objet est un bonus de vie.
     *
     * @return false car ce bonus n'est pas un bonus de vie.
     */
    @Override
    public boolean isLifeBonus() {
        return false;
    }

    /**
     * Indique si l'objet est un bonus de bombe.
     *
     * @return false car ce bonus n'est pas un bonus de bombe.
     */
    @Override
    public boolean isBombBonus() {
        return false;
    }

    /**
     * Dégrade le mur.
     *
     * @param wall le mur à dégrader.
     */
    @Override
    public void degrade(Wall wall) {
        // Implémentation vide car ce bonus ne dégrade pas les murs
    }
}