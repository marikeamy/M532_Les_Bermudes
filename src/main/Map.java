package main;

import java.util.List;

public class Map {
    private int xSize;
    private int ySize;
    private List<List<Location>> locationGrid;

    public Map(int xSize, int ySize, List<List<Location>> locationGrid) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.locationGrid = locationGrid;
    }

    public int getXSize() {
        return this.xSize;
    }

    public int getYSize() {
        return this.ySize;
    }

    public List<List<Location>> getLocationGrid() {
        return this.locationGrid;
    }
}
