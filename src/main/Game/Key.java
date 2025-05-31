package main.Game;

import java.util.List;

public class Key extends Item{
  /*------------Code Marike----------------
    //clé doit être reliée à Location.
    //clé doit porter le nom de Location qu'elle ouvre.
    //lui donenr la worldMap
    WorldMap map;
    Player player;

    public Key (String name, String description, WorldMap map, Player player){
        super(name, description);
        this.map = map;
        this.player = player;
    }

    @Override
    public void interactionsWithItem() {
        throw new UnsupportedOperationException("Unimplemented method 'interactionsWithItem'");
    }
} */

/*----------Test Tess------------- */
    private String locationNameToUnlock;
    private WorldMap map;

    public Key(String name, String description, String locationNameToUnlock, WorldMap map) {
        super(name, description);
        this.locationNameToUnlock = locationNameToUnlock;
        this.map = map;
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

    @Override
    public void interactionsWithItem() {
        unlockLocation(); // non utilisé dans la commande pour l’instant
    }
}
