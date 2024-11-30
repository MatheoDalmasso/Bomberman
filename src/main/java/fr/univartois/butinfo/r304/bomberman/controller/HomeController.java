package fr.univartois.butinfo.r304.bomberman.controller;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.map.mapGenerator.MapGeneratorGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.mapGenerator.generator.MapGeneratorGenerator1;
import fr.univartois.butinfo.r304.bomberman.model.map.mapGenerator.generator.MapGeneratorGenerator2;
import fr.univartois.butinfo.r304.bomberman.model.map.mapGenerator.generator.MapGeneratorGenerator3;
import fr.univartois.butinfo.r304.bomberman.model.map.mapGenerator.generator.MapGeneratorGenerator4;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    SpriteStore spriteStore = SpriteStore.getInstance();

    @FXML
    public Button buttonLevel1;

    @FXML
    public Button buttonLevel2;

    @FXML
    public Button buttonLevel3;

    @FXML
    public Button buttonLevel4;

    @FXML
    public void onClickEasy(ActionEvent actionEvent) throws IOException {
        MapGeneratorGenerator mapGenerator = new MapGeneratorGenerator1(1080, 720);
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/univartois/butinfo/r304/bomberman/view/bomberman.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) buttonLevel1.getScene().getWindow();
        BombermanController controller = loader.getController();

        BombermanGame game = new BombermanGame(1080, 720, spriteStore, 3, 0, 0);

        game.setGenerateurMap(mapGenerator);
        game.setController(controller);
        game.setDifficultyLevel(1);
        controller.setGame(game);
        controller.setStage(stage);
        game.prepare(1);
        stage.setScene(scene);

    }

    @FXML
    public void onClickMedium(ActionEvent actionEvent) throws IOException {
        MapGeneratorGenerator mapGenerator = new MapGeneratorGenerator2(1080, 720);
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/univartois/butinfo/r304/bomberman/view/bomberman.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) buttonLevel2.getScene().getWindow();
        BombermanController controller = loader.getController();

        BombermanGame game = new BombermanGame(1080, 720, spriteStore, 3, 0, 1);

        game.setGenerateurMap(mapGenerator);
        game.setController(controller);
        game.setDifficultyLevel(2);
        controller.setGame(game);
        controller.setStage(stage);
        game.prepare(2);
        stage.setScene(scene);
    }

    @FXML
    public void onClickHard(ActionEvent actionEvent) throws IOException {
        MapGeneratorGenerator mapGenerator = new MapGeneratorGenerator3(1080, 720);
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/univartois/butinfo/r304/bomberman/view/bomberman.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) buttonLevel3.getScene().getWindow();
        BombermanController controller = loader.getController();

        BombermanGame game = new BombermanGame(1080, 720, spriteStore, 5, 0, 2);

        game.setGenerateurMap(mapGenerator);
        game.setController(controller);
        game.setDifficultyLevel(3);
        controller.setGame(game);
        controller.setStage(stage);
        game.prepare(3);
        stage.setScene(scene);

    }

    @FXML
    public void onClickImpossible(ActionEvent actionEvent) throws IOException {
        MapGeneratorGenerator mapGenerator = new MapGeneratorGenerator4(1080, 720);
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/univartois/butinfo/r304/bomberman/view/bomberman.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) buttonLevel4.getScene().getWindow();
        BombermanController controller = loader.getController();

        BombermanGame game = new BombermanGame(1080, 720, spriteStore, 8, 1, 3);

        game.setGenerateurMap(mapGenerator);
        game.setController(controller);
        game.setDifficultyLevel(4);
        controller.setGame(game);
        controller.setStage(stage);
        game.prepare(4);
        stage.setScene(scene);

    }
}