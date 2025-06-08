package main.Commands;

import java.util.Iterator;
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
            printOutput("There's no item to take here!");
        } else {
            Iterator<Item> iterator = itemList.iterator();
            while (iterator.hasNext()) {
                Item i = iterator.next();
                if (argument.equals(i.getName().toLowerCase())) {
                    inventory.addItem(i);
                    iterator.remove();
                    itemTaken = i.getName();
                    if (i.getName().equalsIgnoreCase("Teleport Crystal")) {
                        Game.getInstance().getCommandsRegistry().addCommand("teleport",
                                new Teleport("You can teleport to any place you have already visited by writing \"Teleport to <name of the place>\"",
                                        "teleport", map, player, player.getInventory()));
                        printOutput("You feel a strange power... You can now teleport! Use the help command to find out how to use the Teleport Crystal.");
                    } else if(i.getName().equalsIgnoreCase("Master Sword")){
                        printOutput("You are the chosen one! You have drawn the Master Sword out of the rock it was plunged in.");
                    } 
                }
            }
        }

        if (itemTaken == null) {
            printOutput("This item is not here.");
        } else {
            printOutput("You put the " + itemTaken + " in your inventory.");
        }

    }

}
