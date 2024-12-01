package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.component.IMapComponent;
import java.util.ArrayList;
import java.util.List;

public class GameMap implements IMapComponent {

    private final int height;
    private final int width;
    private final Cell[][] cells;
    private final List<IMapComponent> components = new ArrayList<>();

    public GameMap(int height, int width) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        init();
    }

    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isOnMap(int row, int column) {
        return ((0 <= row) && (row < height)) && ((0 <= column) && (column < width));
    }

    public Cell getAt(int row, int column) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        return cells[row][column];
    }

    public void setAt(int row, int column, Cell cell) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        cells[row][column].replaceBy(cell);
    }

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

    @Override
    public void add(IMapComponent component) {
        components.add(component);
    }

    @Override
    public void remove(IMapComponent component) {
        components.remove(component);
    }

    @Override
    public IMapComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public void display() {
        for (IMapComponent component : components) {
            component.display();
        }
    }


}