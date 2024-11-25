package fr.univartois.butinfo.r304.bomberman.controller;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.map.*;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController {

    @FXML
    public Button buttonLevel1;

    @FXML
    public Button buttonLevel2;

    @FXML
    public Button buttonLevel3;

    @FXML
    public void onClickEasy(ActionEvent actionEvent) throws IOException {
        startGame(new GenerateurMap1(1080, 720), 1);
    }

    @FXML
    public void onClickMedium(ActionEvent actionEvent) throws IOException {
        startGame(new GenerateurMap2(1080, 720), 2);
    }

    @FXML
    public void onClickHard(ActionEvent actionEvent) throws IOException {
        startGame(new GenerateurMap3(1080, 720), 3);
    }

    private void startGame(GenerateurMap mapGenerator, int level) throws IOException {
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/univartois/butinfo/r304/bomberman/view/bomberman.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) buttonLevel1.getScene().getWindow();
        BombermanController controller = loader.getController();

        BombermanGame game = new BombermanGame(1080, 720, new SpriteStore(), 3);
        game.setGenerateurMap(mapGenerator);
        game.setController(controller);
        controller.setStage(stage);
        controller.setGame(game);

        switch (level) {
            case 1:
                game.prepare1();
                break;
            case 2:
                game.prepare2();
                break;
            case 3:
                game.prepare3();
                break;
        }

        stage.setScene(scene);

        switch (level) {
            case 1:
                game.start(1);
                break;
            case 2:
                game.start(2);
                break;
            case 3:
                game.start(3);
                break;
        }

    }
}