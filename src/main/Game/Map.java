package main.Game;

import java.util.List;

public class Map {
    private List<List<Location>> locationGrid;

    public Map(List<List<Location>> locationGrid) {
        this.locationGrid = locationGrid;
    }

    public List<List<Location>> getLocationGrid() {
        return this.locationGrid;
    }
}
