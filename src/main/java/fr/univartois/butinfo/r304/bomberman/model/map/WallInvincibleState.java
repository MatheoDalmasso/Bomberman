package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class WallInvincibleState implements IWallState {

    private final Sprite sprite;

    public WallInvincibleState(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void degrade(Wall wall) {
        // Do nothing, mur incassable
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }


}
