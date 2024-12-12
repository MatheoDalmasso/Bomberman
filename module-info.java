module fr.univartois.butinfo.r304.bomberman {
    exports fr.univartois.butinfo.r304.bomberman;
    exports fr.univartois.butinfo.r304.bomberman.model.map;
    exports fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator;
    exports fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.factory;
    exports fr.univartois.butinfo.r304.bomberman.view;

    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    opens fr.univartois.butinfo.r304.bomberman to javafx.fxml;
}