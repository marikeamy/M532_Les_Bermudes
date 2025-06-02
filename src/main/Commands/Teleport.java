package main.Commands;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
