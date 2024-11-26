package fr.univartois.butinfo.r304.bomberman.model.map;


import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class Lava implements IWallState {

    private final Sprite sprite;

    public Lava(Sprite sprite) {
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
}
