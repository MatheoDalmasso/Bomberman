package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.component.IMapComponent;
import java.util.ArrayList;
import java.util.List;

public class GameMap implements IMapComponent {

    /**
     * The height of the map.
     */
    private final int height;
    /**
     * The height of the map.
     */

    private final int width;
    /**
     * The cells of the map.
     */

    private final Cell[][] cells;
    /**
     * The list of components on the map.
     */

    private final List<IMapComponent> components = new ArrayList<>();

    /**
     * Create a new map with the specified height and width.
     * @param height
     * @param width
     */
    public GameMap(int height, int width) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        init();
    }

    /**
     * Initialize the map.
     */
    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Get the height of the map.
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the width of the map.
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Check if the specified location is on the map.
     * @param row
     * @param column
     * @return
     */
    public boolean isOnMap(int row, int column) {
        return ((0 <= row) && (row < height)) && ((0 <= column) && (column < width));
    }

    /**
     * Get the cell at the specified location.
     * @param row
     * @param column
     * @return
     */
    public Cell getAt(int row, int column) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        return cells[row][column];
    }

    /**
     * Set the cell at the specified location.
     * @param row
     * @param column
     * @param cell
     */
    public void setAt(int row, int column, Cell cell) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        cells[row][column].replaceBy(cell);
    }

    /**
     * Get the empty cells on the map.
     * @return
     */
    public List<Cell> getEmptyCells() {
        List<Cell> emptyTiles = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isEmpty()) {
                    emptyTiles.add(cells[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    /**
     * Add a component to the map.
     * @param component
     */
    @Override
    public void add(IMapComponent component) {
        components.add(component);
    }

    /**
     * Remove the specified component from the map.
     * @param component
     */
    @Override
    public void remove(IMapComponent component) {
        components.remove(component);
    }

    /**
     * Get the child at the specified index.
     * @param index
     * @return
     */
    @Override
    public IMapComponent getChild(int index) {
        return components.get(index);
    }

    /**
     * Display the map and all its components.
     */
    @Override
    public void display() {
        for (IMapComponent component : components) {
            component.display();
        }
    }


}