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


import fr.univartois.butinfo.r304.bomberman.model.bombs.Bomb;
import fr.univartois.butinfo.r304.bomberman.model.bombs.typebomb.BigBomb;
import fr.univartois.butinfo.r304.bomberman.model.bombs.typebomb.FakeBomb;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.IMapGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.factory.*;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.Enemy;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.life.EnemyWithLife;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movement.InteligentMovement;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movement.RandomMovement;
import fr.univartois.butinfo.r304.bomberman.model.movables.player.Player;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private static final Random RANDOM = new Random();

    /**
     * La vitesse de déplacement du joueur (en pixels/s).
     */
    private static final int DEFAULT_SPEED = 75;

    private static final String INVALID_DIFFICULTY_LEVEL = "Invalid difficulty level: ";

    /**
     * Le nombre de bombes initialement disponibles pour le joueur.
     */
    private static final int DEFAULT_BOMBS = 5;

    /**
     * La largeur de la carte du jeu (en pixels).
     */
    private final int width;

    /**
     * La hauteur de la carte du jeu (en pixels).
     */
    private final int height;

    /**
     * L'instance de {@link IMapFactory} permettant de créer les {@link GameMap} du jeu.
     */
    private IMapFactory mapFactory;

    /**
     * L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu.
     */
    private final ISpriteStore spriteStore;
    /**
     * Le nombre de bombes restantes du joueur.
     */
    private final IntegerProperty remainingBombs = new SimpleIntegerProperty(DEFAULT_BOMBS);
    /**
     * Le nombre d'ennemis initialement dans le jeu.
     */
    private final int nbEnemies;

    /**
     * Le nombre de boss initialement dans le jeu.
     */
    private final int nbBoss;

    /**
     * Le nombre de sous boss initialement dans le jeu.
     */
    private final int nbSousBoss;
    /**
     * La liste des objets pouvant se déplacer dans le jeu.
     */
    private final List<IMovable> movableObjects = new CopyOnWriteArrayList<>();
    /**
     * L'animation du jeu, qui s'assure que les différents objets se déplacent.
     */
    private final AnimationTimer animation = new BombermanAnimation(movableObjects);
    /**
     * La carte du jeu.
     */
    private GameMap gameMap;
    /**
     * Le personnage du joueur.
     */
    private Player player;
    /**
     * Le nombre d'ennemis restant dans le jeu.
     */
    private int remainingEnemies;

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
    private IMapGenerator generateurMap; // NOSONAR

    /**
     * Le niveau de difficulté
     */
    private int difficultyLevel;

    /**
     * Crée une nouvelle instance de BombermanGame.
     *
     * @param gameWidth   La largeur de la carte du jeu.
     * @param gameHeight  La hauteur de la carte du jeu.
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *                    {@link Sprite} du jeu.
     * @param nbEnemies   Le nombre d'ennemis dans le jeu.
     */
    public BombermanGame(int gameWidth, int gameHeight, ISpriteStore spriteStore, int nbEnemies, int nbBoss, int nbSousBoss) {
        this.width = gameWidth;
        this.height = gameHeight;
        this.spriteStore = spriteStore;
        this.nbEnemies = nbEnemies;
        this.nbBoss = nbBoss;
        this.nbSousBoss = nbSousBoss;
        GetGameInstance.setInstance(this); // Donne l'instance de BombermanGame
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

    public Player getPlayer() {
        return player;
    }

    /**
     * Incrémente le nombre de bombes restantes du joueur.
     */
    public void incrementBombCount() {
        remainingBombs.set(remainingBombs.get() + 1);
        addBombToPlayer();
    }

    /**
     * Ajoute une bombe au joueur.
     */
    private void addBombToPlayer() {
        Bomb bomb = new Bomb(this, player.getX(), player.getY(), spriteStore.getSprite("bomb"), 4000);
        player.addBomb(bomb);
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

    /**
     * Donne le générateur de la carte du jeu.
     *
     * @return Le générateur de la carte du jeu.
     */
    public void setGenerateurMap(IMapGenerator generateurMap) {
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

    public void setMapFactory(IMapFactory mapFactory) {
        this.mapFactory = mapFactory;
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
     * Prépare une nouvelle partie de Bomberman.
     *
     * @param difficultyLevel Le niveau de difficulté de la partie.
     */
    public void prepare(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1:
                gameMap = createMap(1);
                break;
            case 2:
                gameMap = createMap(2);
                break;
            case 3:
                gameMap = createMap(3);
                break;
            case 4:
                gameMap = createMap(4);
                controller.prepare(gameMap);
                break;
            default:
                throw new IllegalArgumentException(INVALID_DIFFICULTY_LEVEL + difficultyLevel);
        }
        controller.prepare(gameMap);
    }

    /**
     * Crée la carte du jeu, en respectant les dimensions de la fenêtre.
     *
     * @return La carte du jeu ayant été créée.
     */
    private GameMap createMap(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1:
                setMapFactory(new MapFactory1(height, width, spriteStore));
                startBombTimer();
                break;
            case 2:
                setMapFactory(new MapFactory2(height, width, spriteStore));
                startBombTimer();
                break;
            case 3:
                setMapFactory(new MapFactory3(height, width, spriteStore));
                startBombTimer();
                break;
            case 4:
                setMapFactory(new MapFactory4(height, width, spriteStore));
                startBombTimer();
                break;
            default:
                throw new IllegalArgumentException(INVALID_DIFFICULTY_LEVEL + difficultyLevel);
        }
        return mapFactory.createMap();
    }

    /**
     * Donne le niveau de difficulté de la partie.
     *
     * @return Le niveau de difficulté de la partie.
     */
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Modifie le niveau de difficulté de la partie.
     *
     * @param difficultyLevel Le nouveau niveau de difficulté de la partie.
     */
    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Démarre la partie de Bomberman.
     */
    public void start(int difficultyLevel) {
        switch (difficultyLevel) {
            case 1:
                prepare(1);
                break;
            case 2:
                prepare(2);
                break;
            case 3:
                prepare(3);
                break;
            case 4:
                prepare(4);
                break;
            default:
                throw new IllegalArgumentException(INVALID_DIFFICULTY_LEVEL + difficultyLevel);
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

        player = new Player(this, 0, 0, spriteStore.getSprite("guy"));
        movableObjects.add(player);
        spawnMovable(player);

        // On ajoute les bombes initiales du joueur.
        for (int i = 0; i < DEFAULT_BOMBS; i++) {
            Bomb bomb = new Bomb(this, player.getX(), player.getY(), spriteStore.getSprite("bomb"), 4000);
            player.addBomb(bomb);
        }

        // On crée ensuite les ennemis sur la carte.
        for (int i = 0; i < nbEnemies; i++) {
            Enemy ennemi = new Enemy(this, 0, 0, spriteStore.getSprite("skeleton"), new RandomMovement());
            IMovable ennemiAvecSante = new EnemyWithLife(ennemi, 1);
            ennemiAvecSante.setHorizontalSpeed(DEFAULT_SPEED);
            movableObjects.add(ennemiAvecSante);
            spawnMovable(ennemiAvecSante);
        }

        // On crée ensuite les boss sur la carte.
        for (int i = 0; i < nbBoss; i++) {
            Enemy boss = new Enemy(this, 0, 0, spriteStore.getSprite("sorcier"), new InteligentMovement(player));
            IMovable bossAvecSante = new EnemyWithLife(boss, 5);
            bossAvecSante.setHorizontalSpeed(DEFAULT_SPEED);
            movableObjects.add(bossAvecSante);
            spawnMovable(bossAvecSante);
        }

        // On crée ensuite les boss sur la carte.
        for (int i = 0; i < nbSousBoss; i++) {
            Enemy sousboss = new Enemy(this, 0, 0, spriteStore.getSprite("yeti"), new InteligentMovement(player));
            IMovable sousBossAvecSante = new EnemyWithLife(sousboss, 3);
            sousBossAvecSante.setHorizontalSpeed(DEFAULT_SPEED);
            movableObjects.add(sousBossAvecSante);
            spawnMovable(sousBossAvecSante);
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

            // Interdire les BigBomb et FakeBomb dans le niveau 1
            if (difficultyLevel == 1 && randomBomb < 2) {
                randomBomb = 4; // Changer la valeur pour éviter de créer une BigBomb ou FakeBomb
            }

            if (randomBomb < 2 && difficultyLevel > 1) {
                if (playerX > spriteSize && playerX < (mapWidth - spriteSize * 2) && playerY > spriteSize && playerY < (mapHeight - spriteSize * 2)) {
                    BigBomb bomb = new BigBomb(this, playerX, playerY, spriteStore.getSprite("large-bomb"), 4000);
                    dropBomb(bomb);
                    player.getBombs().removeFirst();
                }
            } else if (randomBomb == 3 && difficultyLevel > 1) {
                FakeBomb bomb = new FakeBomb(this, player.getX(), player.getY(), spriteStore.getSprite("pool_ball"), 4000);
                dropBomb(bomb);
                player.getBombs().removeFirst();
            } else {
                Bomb bomb = player.getBombs().removeFirst();
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
    public void dropBomb(Bomb bomb) {
        Cell cell = getCellOf(player);

        bomb.setX(cell.getColumn() * spriteStore.getSpriteSize());
        bomb.setY(cell.getRow() * spriteStore.getSpriteSize());
        this.addMovable(bomb);
        bomb.move(0);
    }

    /**
     * Dépose une BigBomb sur la tuile où se trouve le joueur
     *
     * @param bomb La bombe à déposer.
     */
    public void dropBomb(BigBomb bomb) {
        Cell cell = getCellOf(player);

        bomb.setX(cell.getColumn() * spriteStore.getSpriteSize());
        bomb.setY(cell.getRow() * spriteStore.getSpriteSize());

        this.addMovable(bomb);
        bomb.move(0);
    }

    /**
     * Dépose une FakeBomb sur la tuile où se trouve le joueur
     *
     * @param bomb La bombe à déposer.
     */
    public void dropBomb(FakeBomb bomb) {
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
    private Cell getCellOf(IMovable movable) {
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
            gameOver("YOU WIN! Returning to the main menu in 2 seconds...");
        }
    }

    /**
     * Termine la partie lorsque le joueur est tué.
     */
    public void playerIsDead() {
        gameOver("YOU HAVE BEEN KILLED! Returning to the main menu in 2 seconds...");
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
