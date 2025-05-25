package main.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldMap {
    private List<List<Location>> locationGrid;

    public WorldMap(List<List<Location>> locationGrid) {
        this.locationGrid = locationGrid;
    }

    public List<List<Location>> getLocationGrid() {
        return this.locationGrid;
    }

    protected void addLocation(Location location, int x, int y) {
        this.locationGrid.get(x).set(y, location);
    }

    protected void setPlayerPosition(Player player, int x, int y) {
        List<Integer> playerPosition = player.getPlayerPosition();
        playerPosition.set(0, x);
        playerPosition.set(1, y);
    }

    public Location getPlayerLocation() {
        int x = Game.getInstance().getPlayer().getPlayerPosition().get(0);
        int y = Game.getInstance().getPlayer().getPlayerPosition().get(1);
        Location location = Game.getInstance().getWorldMap().getLocationGrid().get(x).get(y);
        return location;
    }
}