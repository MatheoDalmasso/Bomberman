package fr.univartois.butinfo.r304.bomberman.model;

/**
 * La classe {@link GetGameInstance} permet de récupérer l'instance du jeu.
 */
public class GetGameInstance {

    /**
     * L'instance du jeu
     */
    private static BombermanGame instance;

    /**
     * Constructeur privé
     */
    private GetGameInstance() {
        // Ne fais rien
    }

    /**
     * Récupère l'instance du jeu
     *
     * @return l'instance du jeu
     */
    public static BombermanGame getInstance() {
        return instance;
    }

    /**
     * Définit l'instance du jeu
     *
     * @param gameInstance l'instance du jeu
     */
    public static void setInstance(BombermanGame gameInstance) {
        instance = gameInstance;
    }

}
