/**
 * Interface IMovementStrategy : permet de déplacer un personnage ennemi.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movement;

import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.Enemy;

/**
 * La méthode de déplacement d'un personnage ennemi.
 */
public interface IMovementStrategy {
    void deplacer(Enemy ennemi, long delta);
}
