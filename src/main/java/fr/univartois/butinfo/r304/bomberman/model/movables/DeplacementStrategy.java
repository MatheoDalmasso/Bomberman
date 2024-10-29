package fr.univartois.butinfo.r304.bomberman.model.movables;

public interface DeplacementStrategy {
    void deplacer(PersonnageEnnemi ennemi, long delta);
}
