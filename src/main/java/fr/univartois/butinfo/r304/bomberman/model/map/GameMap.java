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
     *
     * @param height The height of the map.
     * @param width  The width of the map.
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
     *
     * @return The height of the map.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the width of the map.
     *
     * @return The width of the map.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the cell at the specified location.
     *
     * @param row    The row of the cell.
     * @param column The column of the cell.
     * @return The cell at the specified location.
     */
    public Cell getAt(int row, int column) {
        incorrectCellLocation(row, column);
        return cells[row][column];
    }

    /**
     * Set the cell at the specified location.
     *
     * @param row    The row of the cell.
     * @param column The column of the cell.
     * @param cell   The cell to set.
     */
    public void setAt(int row, int column, Cell cell) {
        incorrectCellLocation(row, column);
        cells[row][column].replaceBy(cell);
    }

    private void incorrectCellLocation(int row, int column) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
    }

    /**
     * Get the empty cells on the map.
     *
     * @return The empty cells on the map.
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
     *
     * @param component The component to add.
     */
    @Override
    public void add(IMapComponent component) {
        components.add(component);
    }

    /**
     * Remove the specified component from the map.
     *
     * @param component The component to remove.
     */
    @Override
    public void remove(IMapComponent component) {
        components.remove(component);
    }

    /**
     * Get the child at the specified index.
     *
     * @param index The index of the child.
     * @return The child at the specified index.
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