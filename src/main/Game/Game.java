package main.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import main.Commands.CommandsRegistry;
import main.Commands.ICommand;

public class Game {
    private static Game instance;

    private Map map;
    private Player player;
    private CommandsRegistry commandsRegistry;

    private Game(Map map, Player player, CommandsRegistry commandsRegistry) {
        System.out.println("Initializing game...");
        this.map = map;
        this.player = player;
        this.commandsRegistry = commandsRegistry;
    }

    public static void run() {
        System.out.println("Running game...");
    }

    public static Game getInstance() {
        if (instance == null) {
            Player player = new Player("Player1", Arrays.asList(2, 2));
            Map map = new Map(new ArrayList<>());
            java.util.Map<String, ICommand> commands = new HashMap<>();
            CommandsRegistry registry = new CommandsRegistry(commands);
            instance = new Game(map, player, registry);
        }
        return instance;
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public CommandsRegistry getCommandsRegistry() {
        return commandsRegistry;
    }
}
