/**
 * Cette classe représente une bombe qui n'explose pas.
 */
package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

import java.util.Objects;

/**
 * Cette classe représente une bombe qui n'explose pas.
 */
public class FakeBombe extends AbstractMovable implements IBombe {
    private long delai;
    private long startTime = -1;

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game      Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite    L'instance de {@link Sprite} représentant l'objet.
     */
    public FakeBombe(BombermanGame game, double xPosition, double yPosition, Sprite sprite, long delai) {
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
        if (elapsedTime >= 4000) {
            game.removeMovable(this);
            game.decreaseBombs();
            return true;
        }
        return false;
    }

    /**
     * Permet de gérer les collisions de cet objet avec un autre objet.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        //
    }

    /**
     * Permet de gérer l'explosion de cet objet.
     */
    @Override
    public void explode() {
        //
    }

    /**
     * Permet de gérer le fait que cet objet touche un ennemi.
     */
    @Override
    public void hitEnemy() {
        //
    }

    /**
     * Check si un objet est egal à un autre
     *
     * @param o L'objet a comparer
     * @return true si les objets sont egaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FakeBombe fakeBombe = (FakeBombe) o;
        return delai == fakeBombe.delai && startTime == fakeBombe.startTime;
    }

    /**
     * Retourne le hashcode de l'objet
     *
     * @return Le hashcode de l'objet
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), delai, startTime);
    }
}
