package fr.univartois.butinfo.r304.bomberman.controller;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.MapGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator1;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator2;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator3;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.generator.MapGenerator4;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.control.ScrollPane;
import java.io.IOException;


public class HomeController {

    /**
     * Instance of the sprite store
     */
    private final SpriteStore spriteStore = SpriteStore.getInstance();
    private Sprite selectedSprite;

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
        game.setSelectedSprite(selectedSprite);  // Pass the selected sprite
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
        game.setSelectedSprite(selectedSprite);  // Pass the selected sprite
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
        game.setSelectedSprite(selectedSprite);  // Pass the selected sprite
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
        game.setSelectedSprite(selectedSprite);  // Pass the selected sprite
        controller.setGame(game);
        controller.setStage(stage);
        game.prepare(4);
        stage.setScene(scene);

    }
    @FXML
    private HBox characterGallery;
    @FXML
    private Label selectedCharacterLabel;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private ImageView characterImageView;

    private int selectedIndex = 0;

    // Chemins relatifs des sprites
    private final Sprite[] spritePaths = {
            spriteStore.getSprite("bomberman_0_0"),
            spriteStore.getSprite("bomberman_0_1"),
            spriteStore.getSprite("bomberman_0_2"),
            spriteStore.getSprite("bomberman_0_3"),
            spriteStore.getSprite("bomberman_0_4"),
            spriteStore.getSprite("bomberman_0_5"),
            spriteStore.getSprite("bomberman_1_0"),
            spriteStore.getSprite("bomberman_1_1"),
            spriteStore.getSprite("bomberman_1_2"),
            spriteStore.getSprite("bomberman_1_3"),
            spriteStore.getSprite("bomberman_1_5")
    };
    @FXML
    private ScrollPane scrollPane;

    // Initialisation
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            // Envoie le ScrollPane vers le tout début du contenu
            characterGallery.requestFocus();
            scrollPane.setVvalue(0); // Essaie de le remettre en haut
        });
        // Afficher le personnage par défaut
        updateCharacterImage();

    // Gestion de la navigation au clavier
        characterGallery.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    onLeftButtonClick(null);
                    break;
                case RIGHT:
                    onRightButtonClick(null);
                    break;
                default:
                    break;
            }
        });

    // Donner le focus à HBox pour écouter les événements clavier
        characterGallery.setFocusTraversable(true);
    }

    // Gestion de la sélection du personnage
    private void selectCharacter(Sprite sprite) {
        selectedSprite = sprite;
        selectedCharacterLabel.setText("Selected Character: " + sprite);
    }

    public Sprite getSelectedSprite() {
        return selectedSprite;
    }

    @FXML
    public void onLeftButtonClick(ActionEvent event) {
        selectedIndex = (selectedIndex - 1 + spritePaths.length) % spritePaths.length;
        updateCharacterImage();
    }
    // Méthodes pour naviguer à droite
    @FXML
    public void onRightButtonClick(ActionEvent event) {
        selectedIndex = (selectedIndex + 1) % spritePaths.length;
        updateCharacterImage();
    }

    private void updateCharacterImage() {
        Sprite sprite = spritePaths[selectedIndex];
        Image image = sprite.getImage();
        Image resizedImage = new Image(image.getUrl(), 250, 250, true, true);
        characterImageView.setImage(resizedImage);
        selectedSprite = sprite;
    }

}
