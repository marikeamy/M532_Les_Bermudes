package main.Commands;

import main.Game.Game;
import main.Game.Map;
import main.Game.Player;

public class Go extends Command {

    private Map map;
    private Player player;

    public Go(String description, String verb, Map map, Player player) {
        super(description, verb);
        this.map = map;
        this.player = player;
    }

    public void execute(String argument) {
        // map.movePlayer(player, argument);
    }
}
