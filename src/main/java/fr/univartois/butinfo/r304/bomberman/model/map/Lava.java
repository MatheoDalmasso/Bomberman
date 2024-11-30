package fr.univartois.butinfo.r304.bomberman.model.map;


import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractIMovableDecorator;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.Joueur;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class Lava extends AbstractMovable implements IWallState {

    private final Sprite sprite;

    public Lava(Sprite sprite, BombermanGame game, double xPosition, double yPosition) {
        super(game, xPosition, yPosition, sprite);
        this.sprite = sprite;
    }

    @Override
    public void degrade(Wall wall) {
        // Do nothing
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void collidedWith(IMovable other) {
        if(other instanceof Joueur joueur) {
            joueur.takeDamage(1);
        }
    }

    @Override
    public void explode() {
        // Do nothing
    }

    @Override
    public void hitEnemy() {
        // Do nothing
    }

}
