package fr.univartois.butinfo.r304.bomberman.model.movables;

public interface PlayerState {
    void takeDamage(Joueur player, int damage);
    void updateAppearance(Joueur player);
}
