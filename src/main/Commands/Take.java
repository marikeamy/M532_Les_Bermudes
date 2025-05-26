package main.Commands;

import main.Game.Player;
import main.Game.WorldMap;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
