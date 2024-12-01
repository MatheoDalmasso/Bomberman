package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.factory;

import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator4;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class MapFactory4 implements IMapFactory {
    private final int height;
    private final int width;
    private final ISpriteStore spriteStore;

    public MapFactory4(int height, int width , ISpriteStore spriteStore) {
        this.height = height;
        this.width = width;
        this.spriteStore = spriteStore;
    }

    @Override
    public GameMap createMap() {
        return new MapGenerator4(height / spriteStore.getSpriteSize(), width / spriteStore.getSpriteSize()).genererMap();
    }
}
