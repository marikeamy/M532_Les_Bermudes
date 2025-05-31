package main.Commands;

import java.util.List;

import main.Game.*;

public class Take extends Command {
    private WorldMap map;
    private Player player;

    public Take(String description, String verb, WorldMap map, Player player) {
        super(description, verb);
        this.map = map;
        this.player = player;
    }

    @Override
    public void execute(String argument) {
        Location currentLocation = map.getPlayerLocation();
        Inventory inventory = player.getInventory();
        List<Item> itemList = currentLocation.getItemList();
        String itemTaken = null;
        if (itemList.isEmpty()) {
            System.out.println("There's no item to take here!");
        } else {
            for (Item i : itemList) {
                if (argument.equals(i.getName().toLowerCase())) {
                    inventory.addItem(i);
                    itemList.remove(i);
                    itemTaken = i.getName();
                }
            }
            if (itemTaken == null) {
                System.out.println("This item is not here.");
            } else {
                System.out.println("You took the " + itemTaken + ".");
            }
        }
    }
}
