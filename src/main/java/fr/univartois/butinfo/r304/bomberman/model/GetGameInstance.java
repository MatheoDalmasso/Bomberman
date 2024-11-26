package fr.univartois.butinfo.r304.bomberman.model;

public class GetGameInstance {

    private static BombermanGame instance;

    private GetGameInstance() {
        // Ne fais rien
    }

    public static BombermanGame getInstance() {
        return instance;
    }

    public static void setInstance(BombermanGame gameInstance) {
        instance = gameInstance;
    }

}
