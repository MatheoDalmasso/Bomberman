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

package fr.univartois.butinfo.r304.bomberman.model;

import fr.univartois.butinfo.r304.bomberman.model.bombs.BigBombe;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bombe;
import fr.univartois.butinfo.r304.bomberman.model.bombs.FakeBombe;
import fr.univartois.butinfo.r304.bomberman.model.map.*;
import fr.univartois.butinfo.r304.bomberman.model.movables.*;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * La classe {@link BombermanGame} gère une partie du jeu Bomberman.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
public final class BombermanGame {


    /**
     * Le génarateur de nombres aléatoires utilisé dans le jeu.
     */
    public static final Random RANDOM = new Random();

    /**
     * La vitesse de déplacement du joueur (en pixels/s).
     */
    public static final int DEFAULT_SPEED = 75;

    /**
     * Le nombre de bombes initialement disponibles pour le joueur.
     */
    public static final int DEFAULT_BOMBS = 5;

    /**
     * La largeur de la carte du jeu (en pixels).
     */
    private final int width;

    /**
     * La hauteur de la carte du jeu (en pixels).
     */
    private final int height;

    /**
     * L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu.
     */
    private final ISpriteStore spriteStore;

    /**
     * La carte du jeu.
     */
    private GameMap gameMap;

    /**
     * Le personnage du joueur.
     */
    private Joueur player;

    private final IntegerProperty remainingBombs = new SimpleIntegerProperty(DEFAULT_BOMBS);

    /**
     * Le nombre d'ennemis initialement dans le jeu.
     */
    private int nbEnemies;

    /**
     * Le nombre d'ennemis restant dans le jeu.
     */
    private int remainingEnemies;

    /**
     * La liste des objets pouvant se déplacer dans le jeu.
     */
    private final List<IMovable> movableObjects = new CopyOnWriteArrayList<>();

    /**
     * L'animation du jeu, qui s'assure que les différents objets se déplacent.
     */
    private final AnimationTimer animation = new BombermanAnimation(movableObjects);

    /**
     * Le nombre de bombes restant au joueur.
     */

    /**
     * Le contrôleur du jeu.
     */
    private IBombermanController controller;

    /**
     * Le générateur de la carte du jeu.
     */
    private IGenerateurMap generateurMap; // NOSONAR

    /**
     * Crée une nouvelle instance de BombermanGame.
     *
     * @param gameWidth   La largeur de la carte du jeu.
     * @param gameHeight  La hauteur de la carte du jeu.
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *                    {@link Sprite} du jeu.
     * @param nbEnemies   Le nombre d'ennemis dans le jeu.
     */
    public BombermanGame(int gameWidth, int gameHeight, ISpriteStore spriteStore, int nbEnemies) {
        this.width = gameWidth;
        this.height = gameHeight;
        this.spriteStore = spriteStore;
        this.nbEnemies = nbEnemies;
    }

    /**
     * Démarre le timer de recupération des bombes.
     */
    private void startBombTimer() {
        Timeline bombTimer;
        bombTimer = new Timeline(new KeyFrame(Duration.seconds(15), event -> incrementBombCount()));
        bombTimer.setCycleCount(Animation.INDEFINITE);
        bombTimer.play();
    }

    /**
     * Incrémente le nombre de bombes restantes du joueur.
     */
    private void incrementBombCount() {
        remainingBombs.set(remainingBombs.get() + 1);
        addBombToPlayer();
    }

    /**
     * Ajoute une bombe au joueur.
     */
    private void addBombToPlayer() {
        Bombe bomb = new Bombe(this, player.getX(), player.getY(), spriteStore.getSprite("bomb"), 4000);
        player.addBombe(bomb);
    }

    private void showGameRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Rules");
        alert.setHeaderText("Welcome to Bomberman!");
        alert.setContentText("""
                Rules:
                1. Use arrow keys to move your character or use z, q, s, d.
                2. Press space to drop a bomb.
                3. Kill all enemies to win the game (you need to kill them with your bombs. Be careful! Your inventory may contain troll bombs that don't cause any damage (they look like billiard balls).
                4. If you lose one life, you will be invulnerable for 5 seconds.
                5. Every 15 seconds, we will give you 1 bomb.

                Good luck!
                """);
        alert.showAndWait();
        startBombTimer();
    }


    /**
     * Donne le reste de bombe du joueur.
     *
     * @return Le reste de bombe du joueur.
     */
    public int getRemainingBombs() {
        return remainingBombs.get();
    }

    /**
     * Diminue le nombre de bombes restantes du joueur.
     */
    public void decreaseBombs() {
        if (remainingBombs.get() > 0) {
            remainingBombs.set(remainingBombs.get() - 1);
        }
    }

    /**
     * Donne le nombre de bombes restantes du joueur.
     *
     * @return Le nombre de bombes restantes du joueur.
     */
    public IntegerProperty remainingBombsProperty() {
        return remainingBombs;
    }

    /**
     * Modifie le contrôleur avec lequel interagir pour mettre à jour l'affichage.
     *
     * @param controller Le contrôleur avec lequel interagir.
     */
    public void setController(IBombermanController controller) {
        this.controller = controller;
    }

    public void setGenerateurMap(IGenerateurMap generateurMap) {
        this.generateurMap = generateurMap;
    }

    /**
     * Donne l'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu..
     *
     * @return L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu..
     */
    public ISpriteStore getSpriteStore() {
        return spriteStore;
    }

    /**
     * Donne la largeur de la carte du jeu (en pixels).
     *
     * @return La largeur de la carte du jeu.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la hauteur de la carte du jeu (en pixels).
     *
     * @return La hauteur de la carte du jeu.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Prépare une partie de Bomberman avant qu'elle ne démarre.
     */
    public void prepare1() {
        gameMap = createMap1();
        controller.prepare(gameMap);
    }
    public void prepare2() {
        gameMap = createMap2();
        controller.prepare(gameMap);
    }
    public void prepare3() {
        gameMap = createMap3();
        controller.prepare(gameMap);
    }
    public void prepare4() {
        gameMap = createMap4();
        controller.prepare(gameMap);
    }

    /**
     * Crée la carte du jeu, en respectant les dimensions de la fenêtre.
     *
     * @return La carte du jeu ayant été créée.
     */
    private GameMap createMap1() {
        GenerateurMap map = new GenerateurMap1(height / getSpriteStore().getSpriteSize(), width / getSpriteStore().getSpriteSize());
        startBombTimer();
        return map.genererMap();

    }

    private GameMap createMap2() {
        GenerateurMap map = new GenerateurMap2(height / getSpriteStore().getSpriteSize(), width / getSpriteStore().getSpriteSize());
        startBombTimer();
        return map.genererMap();
    }

    private GameMap createMap3() {
        GenerateurMap map = new GenerateurMap3(height / getSpriteStore().getSpriteSize(), width / getSpriteStore().getSpriteSize());
        startBombTimer();
        return map.genererMap();
    }

    private GameMap createMap4() {
        GenerateurMap map = new GenerateurMap4(height / getSpriteStore().getSpriteSize(), width / getSpriteStore().getSpriteSize());
        startBombTimer();
        return map.genererMap();
    }

    private int difficultyLevel;

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Démarre la partie de Bomberman.
     */
    public void start(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1:
                prepare1();
                break;
            case 2:
                prepare2();
                break;
            case 3:
                prepare3();
                break;
            case 4:
                prepare4();
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level: " + difficultyLevel);
        }
        createMovables();
        initStatistics();
        animation.start();
    }


    /**
     * Crée les différents objets présents au début de la partie et pouvant se déplacer.
     */
    private void createMovables() {
        // On commence par enlever tous les éléments mobiles encore présents.
        clearAllMovables();

        player = new Joueur(this, 0, 0, spriteStore.getSprite("guy"));
        movableObjects.add(player);
        spawnMovable(player);

        // On ajoute les bombes initiales du joueur.
        for (int i = 0; i < DEFAULT_BOMBS; i++) {
            Bombe bomb = new Bombe(this, player.getX(), player.getY(), spriteStore.getSprite("bomb"), 4000);
            player.addBombe(bomb);
        }

        // On crée ensuite les ennemis sur la carte.
        for (int i = 0; i < nbEnemies; i++) {
            PersonnageEnnemi ennemi;
            if (difficultyLevel == 1) {
                ennemi = new PersonnageEnnemi(this, 0, 0, spriteStore.getSprite("goblin"), new DeplacementAleatoire());
            } else if (difficultyLevel == 3 || difficultyLevel == 4) {
                ennemi = new PersonnageEnnemi(this, 0, 0, spriteStore.getSprite("goblin"), new DeplacementIntelligent(player));
            } else {
                // Default to random movement for other levels
                ennemi = new PersonnageEnnemi(this, 0, 0, spriteStore.getSprite("goblin"), new DeplacementAleatoire());
            }
            int initialLife;
            if (difficultyLevel == 1) {
                initialLife = 1;
            } else if (difficultyLevel == 2) {
                initialLife = 2;
            } else if (difficultyLevel == 3 || difficultyLevel == 4) {
                initialLife = 3;
            } else {
                initialLife = 2; // Valeur par défaut si le niveau de difficulté n'est pas spécifié
            }
            IMovable ennemiAvecSante = new EnemyWithLife(ennemi, initialLife);
            ennemiAvecSante.setHorizontalSpeed(DEFAULT_SPEED);
            movableObjects.add(ennemiAvecSante);
            spawnMovable(ennemiAvecSante);
        }
    }


    /**
     * Initialise les statistiques de cette partie.
     */
    private void initStatistics() {
        controller.bindLife(player.pointsDeVieProperty());
        controller.bindScore(player.scoreProperty());
        controller.bindBombs(remainingBombs);
        remainingEnemies = nbEnemies;
    }

    /**
     * Fait apparaître un objet pouvant se déplacer sur la carte du jeu.
     *
     * @param movable L'objet à faire apparaître.
     */
    private void spawnMovable(IMovable movable) {
        List<Cell> spawnableCells = gameMap.getEmptyCells();
        if (!spawnableCells.isEmpty()) {
            Cell cell = spawnableCells.get(RANDOM.nextInt(spawnableCells.size()));
            movable.setX(cell.getColumn() * spriteStore.getSpriteSize());
            movable.setY(cell.getRow() * spriteStore.getSpriteSize());
            addMovable(movable);
        }
    }

    /**
     * Déplace le personnage du joueur vers le haut.
     */
    public void moveUp() {
        stopMoving();
        player.setVerticalSpeed(-DEFAULT_SPEED);
    }

    /**
     * Déplace le personnage du joueur vers la droite.
     */
    public void moveRight() {
        stopMoving();
        player.setHorizontalSpeed(DEFAULT_SPEED);
    }

    /**
     * Déplace le personnage du joueur vers le bas.
     */
    public void moveDown() {
        stopMoving();
        player.setVerticalSpeed(DEFAULT_SPEED);
    }

    /**
     * Déplace le personnage du joueur vers la gauche.
     */
    public void moveLeft() {
        stopMoving();
        player.setHorizontalSpeed(-DEFAULT_SPEED);
    }

    /**
     * Arrête le déplacement du joueur.
     */
    public void stopMoving() {
        player.setVerticalSpeed(0);
        player.setHorizontalSpeed(0);
    }

    /**
     * Dépose une bombe sur la tuile où se trouve le joueur, et programme l'explosion de
     * cette bombe.
     */
    public void dropBomb() {
        if (!player.getBombs().isEmpty()) {
            int randomBomb = RANDOM.nextInt(10);
            int playerX = player.getX();
            int playerY = player.getY();
            int spriteSize = spriteStore.getSpriteSize();
            int mapWidth = getWidth();
            int mapHeight = getHeight();

            // Interdire les BigBombe et FakeBombe dans le niveau 1
            if (difficultyLevel == 1 && randomBomb < 2) {
                randomBomb = 4; // Changer la valeur pour éviter de créer une BigBombe ou FakeBombe
            }

            if (randomBomb < 2 && difficultyLevel > 1) {
                if (playerX > spriteSize && playerX < (mapWidth - spriteSize * 2) && playerY > spriteSize && playerY < (mapHeight - spriteSize * 2)) {
                    BigBombe bomb = new BigBombe(this, playerX, playerY, spriteStore.getSprite("large-bomb"), 4000);
                    dropBomb(bomb);
                    player.getBombs().removeFirst();
                }
            } else if (randomBomb == 3 && difficultyLevel > 1) {
                FakeBombe bomb = new FakeBombe(this, player.getX(), player.getY(), spriteStore.getSprite("pool_ball"), 4000);
                dropBomb(bomb);
                player.getBombs().removeFirst();
            } else {
                Bombe bomb = player.getBombs().removeFirst();
                dropBomb(bomb);
            }
        }
    }

    /**
     * Dépose une bombe sur la tuile où se trouve le joueur, et programme l'explosion de
     * cette bombe.
     *
     * @param bomb La bombe à déposer.
     */
    public void dropBomb(Bombe bomb) {
        Cell cell = getCellOf(player);

        bomb.setX(cell.getColumn() * spriteStore.getSpriteSize());
        bomb.setY(cell.getRow() * spriteStore.getSpriteSize());
        this.addMovable(bomb);
        bomb.move(0);
    }

    /**
     * Dépose une BigBombe sur la tuile où se trouve le joueur
     *
     * @param bomb La bombe à déposer.
     */
    public void dropBomb(BigBombe bomb) {
        Cell cell = getCellOf(player);

        bomb.setX(cell.getColumn() * spriteStore.getSpriteSize());
        bomb.setY(cell.getRow() * spriteStore.getSpriteSize());

        this.addMovable(bomb);
        bomb.move(0);
    }

    /**
     * Dépose une FakeBombe sur la tuile où se trouve le joueur
     *
     * @param bomb La bombe à déposer.
     */
    public void dropBomb(FakeBombe bomb) {
        Cell cell = getCellOf(player);

        bomb.setX(cell.getColumn() * spriteStore.getSpriteSize());
        bomb.setY(cell.getRow() * spriteStore.getSpriteSize());

        this.addMovable(bomb);
        bomb.move(0);
    }

    /**
     * Donne la carte du jeu.
     *
     * @return La carte du jeu.
     */
    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * Récupére la cellule correspondant à la position d'un objet mobile.
     * Il s'agit de la cellule sur laquelle l'objet en question occupe le plus de place.
     *
     * @param movable L'objet mobile dont la cellule doit être récupérée.
     * @return La cellule occupée par l'objet mobile.
     */
    protected Cell getCellOf(IMovable movable) {
        // On commence par récupérer la position du centre de l'objet.
        int midX = movable.getX() + (movable.getWidth() / 2);
        int midY = movable.getY() + (movable.getHeight() / 2);
        return getCellAt(midX, midY);
    }

    /**
     * Donne la cellule à la position donnée sur la carte.
     *
     * @param x La position en x de la cellule.
     * @param y La position en y de la cellule.
     * @return La cellule à la position donnée.
     */
    public Cell getCellAt(int x, int y) {
        // On traduit cette position en position dans la carte.
        int row = y / spriteStore.getSpriteSize();
        int column = x / spriteStore.getSpriteSize();

        // On récupère enfin la cellule à cette position dans la carte.
        return gameMap.getAt(row, column);
    }

    /**
     * Ajoute un objet pouvant se déplacer dans le jeu.
     *
     * @param object L'objet à ajouter.
     */
    public void addMovable(IMovable object) {
        movableObjects.add(object);
        controller.addMovable(object);
    }

    /**
     * Supprime un objet pouvant se déplacer dans le jeu.
     *
     * @param object L'objet à supprimer.
     */
    public void removeMovable(IMovable object) {
        movableObjects.remove(object);
        object.consume();
    }

    /**
     * Supprime tous les objets pouvant se déplacer dans le jeu.
     */
    private void clearAllMovables() {
        for (IMovable movable : movableObjects) {
            movable.consume();
        }
        movableObjects.clear();
    }

    /**
     * Met à jour le score du joueur lorsqu'un ennemi est tué.
     * Si c'était le dernier, le joueur gagne la partie.
     *
     * @param enemy L'ennemi qui a été tué.
     */
    public void enemyIsDead(IMovable enemy) {
        player.incrementScore(1);
        remainingEnemies--;
        removeMovable(enemy);

        if (remainingEnemies == 0) {
            // Tous les aliens ont été tués : la partie est terminée.
            gameOver("YOU WIN!");
        }
    }

    /**
     * Termine la partie lorsque le joueur est tué.
     */
    public void playerIsDead() {
        gameOver("YOU HAVE BEEN KILLED!");
    }

    /**
     * Termine la partie en cours.
     *
     * @param message Le message indiquant le résultat de la partie.
     */
    private void gameOver(String message) {
        animation.stop();
        controller.gameOver(message);
    }

}
