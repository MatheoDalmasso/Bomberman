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

package fr.univartois.butinfo.r304.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La classe Bomberman permet de lancer l'implantation en JavaFX du jeu Bomberman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public final class Bomberman extends Application {

    /**
     * La largeur (en pixels) de la fenêtre affichant le jeu.
     */
    private static final int GAME_WIDTH = 1080;

    /**
     * La hauteur (en pixels) de la fenêtre affichant le jeu.
     */
    private static final int GAME_HEIGHT = 720;

    /**
     * Le nombre d'ennemis à combattre dans le jeu.
     */
    private static final int NB_ENEMIES = 3;

    /**
     * Crée une partie du jeu Bomberman.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the HomeController view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/accueil.fxml"));
        Parent viewContent = fxmlLoader.load();

        // Set the scene with the loaded view
        Scene scene = new Scene(viewContent, GAME_WIDTH, GAME_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("BombermanFX");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Exécute l'application JavaFX du jeu Bomberman.
     *
     * @param args Les arguments de la ligne de commande (dont on ne tient pas compte).
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }

}
