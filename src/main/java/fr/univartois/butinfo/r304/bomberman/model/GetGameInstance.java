package fr.univartois.butinfo.r304.bomberman.model;

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
     *  Récupère l'instance du jeu
     * @return
     */
    public static BombermanGame getInstance() {
        return instance;
    }

    /**
     * Définit l'instance du jeu
     * @param gameInstance
     */
    public static void setInstance(BombermanGame gameInstance) {
        instance = gameInstance;
    }

}
