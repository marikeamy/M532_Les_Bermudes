package main.Commands;

import java.util.List;

import main.Game.*;

public class Teleport extends Command {
    private WorldMap map;
    private Player player;
    private Inventory inventory;

    public Teleport(String description, String verb, WorldMap map, Player player, Inventory inventory) {
        super(description, verb);
        this.map = map;
        this.player = player;
        this.inventory = inventory;
    }

    @Override
    public void execute(String argument) {
        List<List<Location>> grid = map.getLocationGrid();
        int foundX = -1;
        int foundY = -1;
        
        if (!argument.toLowerCase().startsWith("to ")) {
            printOutput("It's not a valid teleport command. Use: teleport to <Name of the location>");
        }

        String destination = argument.substring(3).trim();

        for (int x = 0; x < grid.size(); x++) {
            List<Location> row = grid.get(x);
            for (int y = 0; y < row.size(); y++) {
                Location l = row.get(y);
                if (l.getName().equalsIgnoreCase(destination)) {
                    foundX = x;
                    foundY = y;
                }
            }
        }

        if (foundX == -1 || foundY == -1) {
            printOutput("This location doesn't exist.");
            return;
        }

        List<Item> checkInventory = this.inventory.getItemList();
        boolean hasTeleportStone = false;
        for (Item i : checkInventory) {
            if (i.getName().equals("Teleport Crystal")) {
                hasTeleportStone = true;
            }
        }
        if (hasTeleportStone) {
            if (!grid.get(foundX).get(foundY).getIsLocked() && grid.get(foundX).get(foundY).wasVisited()) {
                player.getPlayerPosition().set(0, foundX);
                player.getPlayerPosition().set(1, foundY);
                printOutput("You teleported to the " + map.getPlayerLocation().getName());
            } else if(!grid.get(foundX).get(foundY).wasVisited()){
                printOutput("You never visited this place. You can't teleport there.");
            }
            else {
                printOutput("The location is completely blocked. You can't teleport there!");
            }
        } else {
            printOutput("You don't have a Teleport Crystal.");
        }
    }
}
