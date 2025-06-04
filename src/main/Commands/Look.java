package main.Commands;

import java.util.List;

import main.Game.*;

/*Tess 25.05.25*/
public class Look extends Command {
    private WorldMap map;

    public Look(String description, String verb, WorldMap map) {
        super(description, verb);
        this.map = map;
    }

    @Override
    public void execute(String argument) {
        Location currentLocation = map.getPlayerLocation();
        List<Item> itemList = currentLocation.getItemList();
        printOutput(currentLocation.getDescription());
        if (!itemList.isEmpty()) {
            printOutput("There is some items laying around:");
            for (Item i : itemList) {
                printOutput(i.getName());
            }
        }
    }
}