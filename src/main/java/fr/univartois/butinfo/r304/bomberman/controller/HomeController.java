package fr.univartois.butinfo.r304.bomberman.controller;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.MapGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator1;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator2;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator3;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator4;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    /**
     * Instance of the sprite store
     */
    SpriteStore spriteStore = SpriteStore.getInstance();
    private static final String BOMBERMAN_FXML = "/fr/univartois/butinfo/r304/bomberman/view/bomberman.fxml";

    /**
     * Buttons for the different levels
     */
    @FXML
    private Button buttonLevel1;

    /**
     * Buttons for the different levels
     */
    @FXML
    private Button buttonLevel2;

    /**
     * Buttons for the different levels
     */
    @FXML
    private Button buttonLevel3;

    /**
     * Buttons for the different levels
     */
    @FXML
    private Button buttonLevel4;

    /**
     * Method to start the game with the easy level
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onClickEasy(ActionEvent actionEvent) throws IOException {
        MapGenerator mapGenerator = new MapGenerator1(1080, 720);
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(BOMBERMAN_FXML));
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

    /**
     * Method to start the game with the medium level
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onClickMedium(ActionEvent actionEvent) throws IOException {
        MapGenerator mapGenerator = new MapGenerator2(1080, 720);
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(BOMBERMAN_FXML));
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

    /**
     * Method to start the game with the hard level
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onClickHard(ActionEvent actionEvent) throws IOException {
        MapGenerator mapGenerator = new MapGenerator3(1080, 720);
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(BOMBERMAN_FXML));
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

    /**
     * Method to start the game with the impossible level
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onClickImpossible(ActionEvent actionEvent) throws IOException {
        MapGenerator mapGenerator = new MapGenerator4(1080, 720);
        mapGenerator.genererMap();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(BOMBERMAN_FXML));
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