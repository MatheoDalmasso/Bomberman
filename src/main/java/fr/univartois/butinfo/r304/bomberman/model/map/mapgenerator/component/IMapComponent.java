package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.component;

/**
 * L'interface IMapComponent représente un composant d'une carte dans le jeu Bomberman.
 * Elle permet de gérer une structure de type composite pour les composants de la carte.
 */
public interface IMapComponent {

    /**
     * Ajoute un composant enfant à ce composant.
     *
     * @param component Le composant enfant à ajouter.
     */
    void add(IMapComponent component);

    /**
     * Supprime un composant enfant de ce composant.
     *
     * @param component Le composant enfant à supprimer.
     */
    void remove(IMapComponent component);

    /**
     * Récupère un composant enfant à l'index spécifié.
     *
     * @param index L'index du composant enfant à récupérer.
     * @return Le composant enfant à l'index spécifié.
     */
    IMapComponent getChild(int index);

    /**
     * Affiche ce composant et ses composants enfants.
     */
    void display();
}