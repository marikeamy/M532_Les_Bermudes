package main.Game;

import java.util.ArrayList;
import java.util.Arrays;

import main.Commands.CommandsRegistry;

public class Game {
    private static Game instance;
    private static Map map;
    private static Player player;
    private static CommandsRegistry commandsRegistry;

    private Game(Map map, Player player, CommandsRegistry commandsRegistry) {
        System.out.println("Initializing game...");
        Game.map = map;
        Game.player = player;
    }

    public static void run() {
        System.out.println("Running game...");
        // your runtime code here...

        // end of game
    }

    public static Game getInstance() {
        if (Game.instance == null) {
          Player player = new Player("Player1", Arrays.asList(2, 2));
          Map map = new Map(new ArrayList<>());
            Game.instance = new Game(map, player, commandsRegistry);
                    }
        return Game.instance;
    }

    public static Map getMap() {
        return map;
    }

    public static Player getPlayer() {
        return player;
    }
}