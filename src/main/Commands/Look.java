package main.Commands;

import main.Game.*;

/*Tess*/
public class Look extends Command {
    private WorldMap map;

    public Look(String description, String verb, WorldMap map) {
        super(description, verb);
        this.map = map;
    }

    @Override
    public void execute(String argument) {
        Location currentLocation = map.getPlayerLocation();
        System.out.println(currentLocation.getDescription());
    }
}