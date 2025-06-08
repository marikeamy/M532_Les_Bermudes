package main.Game;

import java.util.List;

public class Key extends Item {

    /*----------Test Tess------------- */
    private String locationNameToUnlock;
    private WorldMap map;
    private Player player;

    public Key(String name, String description, String locationNameToUnlock, WorldMap map, Player player) {
        super(name, description);
        this.locationNameToUnlock = locationNameToUnlock;
        this.map = map;
        this.player = player;
    }

    public String getLocationNameToUnlock() {
        return locationNameToUnlock;
    }

    public boolean unlockLocation() {
        List<List<Location>> grid = map.getLocationGrid();
        for (int i = 0; i < grid.size(); i++) {
            List<Location> row = grid.get(i);
            for (int j = 0; j < row.size(); j++) {
                Location location = row.get(j);
                if (location.getName().equalsIgnoreCase(locationNameToUnlock)) {
                    location.unlock();
                    return true;
                }
            }
        }
        return false; // Si la "Location" avec ce nom n'existe pas
    }

    public boolean playerIsAdjacentToLocation(Key k) {
        int positionY = player.getPlayerPosition().get(0);
        int positionX = player.getPlayerPosition().get(1);
        List<List<Location>> grid = map.getLocationGrid();

        if (positionY > 0) {
            List<Location> rowAbove = grid.get(positionY - 1);
            if (positionX >= 0 && positionX < rowAbove.size()
                    && rowAbove.get(positionX).getName().equalsIgnoreCase(k.getLocationNameToUnlock())) {
                return true;
            }
        }

        if (positionY + 1 < grid.size()) {
            List<Location> rowUnder = grid.get(positionY + 1);
            if (positionX >= 0 && positionX < rowUnder.size()
                    && rowUnder.get(positionX).getName().equalsIgnoreCase(k.getLocationNameToUnlock())) {
                return true;
            }
        }

        if (positionX > 0
                && grid.get(positionY).get(positionX - 1).getName().equalsIgnoreCase(k.getLocationNameToUnlock())) {
            return true;
        }

        if (positionX + 1 < grid.get(positionY).size()
                && grid.get(positionY).get(positionX + 1).getName().equalsIgnoreCase(k.getLocationNameToUnlock())) {
            return true;
        }

        return false;
    }
}
