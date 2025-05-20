package main.Game;

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
            Game.instance = new Game(map, player, commandsRegistry);
        }
        return Game.instance;
    }
}