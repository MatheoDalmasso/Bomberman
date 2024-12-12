module fr.univartois.butinfo.r304.bomberman {
    exports fr.univartois.butinfo.r304.bomberman;
    exports fr.univartois.butinfo.r304.bomberman.model.map;
    exports fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator;
    exports fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.factory;
    exports fr.univartois.butinfo.r304.bomberman.view;
    exports fr.univartois.butinfo.r304.bomberman.controller to javafx.fxml;

    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.logging;
    opens fr.univartois.butinfo.r304.bomberman to javafx.fxml;
    opens fr.univartois.butinfo.r304.bomberman.controller to javafx.fxml;
}