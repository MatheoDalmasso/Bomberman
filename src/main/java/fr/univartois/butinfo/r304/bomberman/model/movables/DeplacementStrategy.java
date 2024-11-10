/**
 * Interface DeplacementStrategy : permet de déplacer un personnage ennemi.
 */
package fr.univartois.butinfo.r304.bomberman.model.movables;

/**
 * La méthode de déplacement d'un personnage ennemi.
 */
public interface DeplacementStrategy {
    void deplacer(PersonnageEnnemi ennemi, long delta);
}
