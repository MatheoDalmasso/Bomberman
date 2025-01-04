package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.component.IMapComponent;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * La classe {@link Cell} représente une cellule de la carte du jeu du Bomberman.
 * <p>
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
 *
 * @version 0.1.0
 */
public class Cell implements IMapComponent {

    /**
     * La ligne où se trouve cette cellule sur la carte.
     */
    private int row;

    /**
     * La colonne où se trouve cette cellule sur la carte.
     */
    private int column;

    /**
     * La propriété contenant le sprite représentant le contenu de cette cellule sur la
     * carte.
     */
    private final ObjectProperty<Sprite> spriteProperty = new SimpleObjectProperty<>();

    /**
     * La propriété contenant le mur présent sur cette cellule sur la carte.
     */
    private final ObjectProperty<Wall> wallProperty = new SimpleObjectProperty<>();

    /**
     * Crée une nouvelle instance de Cell.
     * La cellule créée est initialement vide.
     *
     * @param row    La ligne où se trouve la cellule sur la carte.
     * @param column La colonne où se trouve la cellule sur la carte.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Crée une nouvelle instance de Cell.
     *
     * @param sprite La représentation du contenu initial de la cellule.
     */
    public Cell(Sprite sprite) {
        this.spriteProperty.set(sprite);
    }

    /**
     * Crée une nouvelle instance de cell.
     *
     * @param wall Le mur initialement présent sur la cellule.
     */
    public Cell(Wall wall) {
        this.wallProperty.set(wall);
        this.spriteProperty.set(wall.getSprite());
    }

    /**
     * Donne la ligne où se trouve cette cellule sur la carte.
     *
     * @return La ligne où se trouve cette cellule sur la carte.
     */
    public int getRow() {
        return row;
    }

    /**
     * Donne la colonne où se trouve cette cellule sur la carte.
     *
     * @return La colonne où se trouve cette cellule sur la carte.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Donne la largeur de cette cellule.
     *
     * @return La largeur de cette cellule.
     */
    public int getWidth() {
        return spriteProperty.get().getWidth();
    }

    /**
     * Donne la hauteur de cette cellule.
     *
     * @return La hauteur de cette cellule.
     */
    public int getHeight() {
        return spriteProperty.get().getHeight();
    }

    /**
     * Vérifie si cette cellule est vide.
     * Une cellule est vide si elle ne contient pas de mur.
     *
     * @return Si la cellule est vide.
     */
    public boolean isEmpty() {
        return wallProperty.get() == null;
    }

    /**
     * Donne le sprite représentant le contenu de cette cellule sur la carte.
     *
     * @return Le sprite représentant le contenu de cette cellule sur la carte.
     */
    public Sprite getSprite() {
        return spriteProperty.get();
    }

    /**
     * Donne la propriété contenant le sprite représentant le contenu de cette cellule sur
     * la carte.
     *
     * @return La propriété contenant le sprite.
     */
    public ObjectProperty<Sprite> getSpriteProperty() {
        return spriteProperty;
    }

    /**
     * Donne le mur présent sur cette cellule sur la carte.
     *
     * @return Le mur présent sur cette cellule sur la carte.
     */
    public Wall getWall() {
        return wallProperty.get();
    }

    /**
     * Remplace le contenu de cette cellule par celui d'une autre cellule.
     *
     * @param cell La cellule dont le contenu doit être copié dans cette cellule.
     */
    public void replaceBy(Cell cell) {
        spriteProperty.set(cell.getSprite());
        wallProperty.set(cell.getWall());
    }

    /**
     * Remplace le contenu de cette cellule par un mur.
     *
     * @param component Le mur à placer sur cette cellule.
     */
    @Override
    public void add(IMapComponent component) {
        throw new UnsupportedOperationException("Cannot add to a leaf component");
    }

    /**
     * Supprime le contenu de cette cellule.
     *
     * @param component Le contenu à supprimer.
     */
    @Override
    public void remove(IMapComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a leaf component");
    }

    /**
     * Donne le composant enfant à l'index spécifié.
     *
     * @param index L'index du composant enfant.
     * @return Le composant enfant à l'index spécifié.
     */
    @Override
    public IMapComponent getChild(int index) {
        throw new UnsupportedOperationException("Cannot get child from a leaf component");
    }

    /**
     * Affiche le contenu de cette cellule.
     */
    @Override
    public void display() {
        // Do nothing
    }
}