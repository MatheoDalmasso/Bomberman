module fr.univartois.butinfo.r304.bomberman {
    exports fr.univartois.butinfo.r304.bomberman;
    exports fr.univartois.butinfo.r304.bomberman.model.map;
    exports fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator;
    exports fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.factory;
    exports fr.univartois.butinfo.r304.bomberman.view;
    exports fr.univartois.butinfo.r304.bomberman.model.movables;
    exports fr.univartois.butinfo.r304.bomberman.model.movables.enemy;
    exports fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movement;
    exports fr.univartois.butinfo.r304.bomberman.model.movables.player;
    exports fr.univartois.butinfo.r304.bomberman.model.bombs;
    exports fr.univartois.butinfo.r304.bomberman.model.bombs.typebomb;
    exports fr.univartois.butinfo.r304.bomberman.model;
    exports fr.univartois.butinfo.r304.bomberman.controller to javafx.fxml;
    
    exports fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.component;
    exports fr.univartois.butinfo.r304.bomberman.model.movables.player.state;
    exports fr.univartois.butinfo.r304.bomberman.model.map.wallstate;

    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.logging;
    opens fr.univartois.butinfo.r304.bomberman to javafx.fxml;
    opens fr.univartois.butinfo.r304.bomberman.controller to javafx.fxml;
}