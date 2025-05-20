package main.Game;

import java.util.List;

public class Map {
    byte x = 3;
    byte y = 3;
    private Location[][] locationGrid = new Location[x][y];

    //constructeur
    public Map(Location[][] locationGrid) {
        this.locationGrid = locationGrid;
    }

    //getter Location
    public Location[][] getLocationGrid() {
        return this.locationGrid;
    }

    public void showMap(int[][] locationGrid) {

    }

}
