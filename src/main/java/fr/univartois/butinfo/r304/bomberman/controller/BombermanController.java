/**
 * Ce logiciel est distribué à des fins éducatives.
 * <p>
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 * <p>
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.bomberman.controller;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IBombermanController;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import javafx.beans.binding.IntegerExpression;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La classe {@link BombermanController} fournit le contrôleur permettant de jouer au jeu
 * du Bomberman dans une interface JavaFX.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public final class BombermanController implements IBombermanController {


    private static final int GAME_WIDTH = 1080;
    private static final int GAME_HEIGHT = 720;

    /**
     * La partie du jeu Bomberman en cours.
     */
    private BombermanGame game;

    /**
     * La fenêtre dans laquelle se déroule le jeu.
     */
    private Stage stage;

    /**
     * Le conteneur affichant l'arrière-plan du jeu.
     */
    @FXML
    private GridPane backgroundPane;

    /**
     * Le conteneur affichant les objets mobiles du jeu.
     */
    @FXML
    private Pane movingPane;

    /**
     * Le label affichant le score du joueur.
     */
    @FXML
    private Label score;

    /**
     * Le label affichant le nombre de bombes du joueur.
     */
    @FXML
    private Label bombs;

    /**
     * Le label affichant le nombre de vies du joueur.
     */
    @FXML
    private Label life;

    /**
     * Le label affichant un message à l'utilisateur.
     */
    @FXML
    private Label message;

    /**
     * Un booléen permettant de savoir si la partie a démarré.
     * Il permet de temporiser le démarrage du jeu, en attendant que l'utilisateur appuie
     * sur une touche de son clavier.
     */
    private boolean started = false;

    /**
     * Associe à ce contrôleur la fenêtre affichant le jeu.
     *
     * @param stage La fenêtre affichant le jeu.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
        addKeyListeners();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.bomberman.model.IBombermanController#setGame(fr.
     * univartois.butinfo.r304.bomberman.model.BombermanGame)
     */
    @Override
    public void setGame(BombermanGame game) {
        this.game = game;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.bomberman.model.IBombermanController#prepare(fr.
     * univartois.butinfo.r304.bomberman.model.map.GameMap)
     */
    @Override
    public void prepare(GameMap map) {
        createBackground(map);
    }

    /**
     * Crée l'arrière-plan du jeu.
     *
     * @param map La carte du jeu à afficher.
     */
    private void createBackground(GameMap map) {
        backgroundPane.getChildren().clear();
        for (int row = 0; row < map.getHeight(); row++) {
            for (int column = 0; column < map.getWidth(); column++) {
                Cell cell = map.getAt(row, column);
                ImageView view = new ImageView(cell.getSprite().getImage());
                cell.getSpriteProperty().addListener((p, o, n) -> view.setImage(n.getImage()));
                backgroundPane.add(view, column, row);
            }
        }

    }

    /**
     * Ajoute les écouteurs de touches pour le jeu.
     */
    private void addKeyListeners() {
        stage.addEventFilter(KeyEvent.KEY_TYPED, this::handleKeyTyped);
        stage.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        stage.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);
    }

    /**
     * Gère l'événement de touche tapée.
     *
     * @param e L'événement de touche tapée.
     */
    private void handleKeyTyped(KeyEvent e) {
        if (!started) {
            // La partie démarre à la première touche appuyée.
            started = true;
            message.setVisible(false);

            // Démarre le jeu avec le niveau de difficulté approprié.
            game.start(game.getDifficultyLevel());
        } else if (" ".equals(e.getCharacter())) {
            // La partie a commencé : il faut déposer une bombe.
            game.dropBomb();
        }
    }

    private void returnToMainMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/univartois/butinfo/r304/bomberman/view/accueil.fxml"));
            Parent viewContent = fxmlLoader.load();
            Scene scene = new Scene(viewContent, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'événement de touche pressée.
     *
     * @param e L'événement de touche pressée.
     */
    private void handleKeyPressed(KeyEvent e) {
        if (started) {
            if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.Z) {
                game.moveUp();
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.Q) {
                game.moveLeft();
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                game.moveDown();
            } else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                game.moveRight();
            }
        }
    }

    /**
     * Gère l'événement de touche relâchée.
     *
     * @param e L'événement de touche relâchée.
     */
    private void handleKeyReleased(KeyEvent e) {
        if (started && (e.getCode().isArrowKey() || e.getCode() == KeyCode.Z || e.getCode() == KeyCode.Q || e.getCode() == KeyCode.S || e.getCode() == KeyCode.D)) {
            game.stopMoving();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.bomberman.model.IBombermanController#bindScore(javafx.
     * beans.binding.IntegerExpression)
     */
    @Override
    public void bindScore(IntegerExpression scoreProperty) {
        score.textProperty().bind(scoreProperty.asString());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.bomberman.model.IBombermanController#bindBombs(javafx.
     * beans.binding.IntegerExpression)
     */
    @Override
    public void bindBombs(IntegerExpression bombsProperty) {
        bombs.textProperty().bind(bombsProperty.asString());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.bomberman.model.IBombermanController#bindLife(javafx.
     * beans.binding.IntegerExpression)
     */
    @Override
    public void bindLife(IntegerExpression lifeProperty) {
        life.textProperty().bind(lifeProperty.asString());
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.bomberman.model.IBombermanController#addMovable(fr.
     * univartois.butinfo.r304.bomberman.model.IMovable)
     */
    @Override
    public void addMovable(IMovable movable) {
        // On affiche l'objet au bon endroit.
        ImageView view = new ImageView(movable.getSprite().getImage());
        view.xProperty().bind(movable.getXProperty());
        view.yProperty().bind(movable.getYProperty());
        movingPane.getChildren().add(view);

        // Lorsque le sprite de l'objet change, son image doit changer également.
        movable.getSpriteProperty().addListener((p, o, n) -> view.setImage(n.getImage()));

        // Lorsque l'objet est consommé, il n'est plus affiché.
        movable.isConsumedProperty().addListener((p, o, n) -> {
            if (n == Boolean.TRUE) {
                movingPane.getChildren().remove(view);
            }
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.bomberman.model.IBombermanController#gameOver(java.lang.
     * String)
     */
    @Override
    public void gameOver(String endMessage) {
        started = false;
        message.setVisible(true);
        returnToMainMenu();
    }



    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.bomberman.model.IBombermanController#reset()
     */
    @Override
    public void reset() {
        movingPane.getChildren().clear();
    }

}
