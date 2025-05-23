package main.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import main.Commands.*;

public class Game {
    private static Game instance;

    private WorldMap map;
    private Player player;
    private CommandsRegistry commandsRegistry;

    private Game(WorldMap map, Player player, CommandsRegistry commandsRegistry) {
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
            Player player = new Player("Player1", Arrays.asList(1, 1));
            WorldMap worldMap = new WorldMap(createAllLocations());
            Map<String, Command> commands = createAllCommands(worldMap, player);
            CommandsRegistry registry = new CommandsRegistry(commands);
            instance = new Game(worldMap, player, registry);
        }
        return instance;
    }

    public WorldMap getWorldMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public CommandsRegistry getCommandsRegistry() {
        return commandsRegistry;
    }

    private static List<List<Location>> createAllLocations() {
        List<List<Location>> grid = new ArrayList<>();

        grid.add(Arrays.asList(
                new Location("Location 1", "You are in a meadow.", false),
                new Location("Location 2", "You are in the woods.", false),
                new Location("Location 3", "You are in a field with a locked house in the middle.", false)));
        grid.add(Arrays.asList(
                new Location("Location 4", "You are in a volcano.", false),
                new Location("Location 5", "You are in a small forest with a river.", false),
                new Location("Location 6", "You see a bridge in the distance.", false)));
        grid.add(Arrays.asList(
                new Location("Location 7", "There's a big wall of stone in front of you.", false),
                new Location("Location 8", "The secret passage goes deep underground.", false),
                new Location("Location 9", "There's a chest full of treasure in front of you!", false)));

        return grid;
    }

    private static Map<String, Command> createAllCommands(WorldMap map, Player player) {
        Map<String, Command> allCommands = new HashMap<>();
        Command commandGo = new Go("You can move north, west, east and south with this command.", "Go", map, player);
        allCommands.put("Go", commandGo);
        return allCommands;
    }

}
