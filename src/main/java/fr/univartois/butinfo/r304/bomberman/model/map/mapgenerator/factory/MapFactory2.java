package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.factory;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator2;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class MapFactory2 implements IMapFactory {
    private final int height;
    private final int width;
    private final ISpriteStore spriteStore;

    public MapFactory2(int height, int width , ISpriteStore spriteStore) {
        this.height = height;
        this.width = width;
        this.spriteStore = spriteStore;
    }

    @Override
    public GameMap createMap() {
        return new MapGenerator2(height / spriteStore.getSpriteSize(), width / spriteStore.getSpriteSize()).genererMap();
    }
}
