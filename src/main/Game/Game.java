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
            addAllItemsToLocation();
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

    private static List<Item> createAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.addAll(Arrays.asList(new Letter("Elven Sword",
                "It's an old elven sword with a riddle inscribed on it: What has a head, a tail, but no body?", "coin"),
                new Letter("Old treasure map",
                        "It's an old treasure map with a volcano drawn on it. It says: Say Merde before the great volcano and a key you shall find.",
                        "merde")));
        return itemList;
    }

    private static List<List<Location>> createAllLocations() {
        List<List<Location>> grid = new ArrayList<>();
        grid.add(Arrays.asList(
                new Location("Peaceful meadow", "You are in a meadow.", false, new ArrayList<>()),
                new Location("Forgotten Woods",
                        "You stand in the Forgotten Woods, where twisted trees whisper secrets of a time long lost.",
                        false, new ArrayList<>()),
                new Location("Old decrepit manor.",
                        "You entered the old decrepit manor. You hear whispers in the dark.", false,
                        new ArrayList<>())));
        grid.add(Arrays.asList(
                new Location("Raging volcano", "You are in a volcano.", false, new ArrayList<>()),
                new Location("Ruins by the river", "You are in a small forest with a river.", false, new ArrayList<>()),
                new Location("Dangerous bridge", "You see a bridge in the distance.", false, new ArrayList<>())));
        grid.add(Arrays.asList(
                new Location("Royal Castle", "There's a big wall of stone in front of you.", false, new ArrayList<>()),
                new Location("Castle floor", "The castle floors seem endless and full of secrets...", true,
                        new ArrayList<>()),
                new Location("Treasure Chamber", "There's a chest full of treasure in front of you! Go grab them!",
                        true,
                        new ArrayList<>())));
        // Nom et description à valider et finir.
        return grid;
    }

    private static void addAllItemsToLocation() {
        List<Item> itemList = createAllItems();
        List<List<Location>> locationGrid = Game.getInstance().getWorldMap().getLocationGrid();
        locationGrid.get(0).get(1).getItemList().add(itemList.get(0));
        locationGrid.get(0).get(1).getItemList().add(itemList.get(1));
        // A finir
    }

    private static Map<String, Command> createAllCommands(WorldMap map, Player player) {
        Map<String, Command> allCommands = new HashMap<>();
        Command commandGo = new Go("You can move north, west, east and south with this command.", "go", map, player);
        allCommands.put("go", commandGo);

        /* Tess 25.05.25 */
        Command commandLook = new Look("Gives you the description of your current location.", "look", map);
        allCommands.put("look", commandLook);

        /* Tess 25.05.25 */
        Command commandHelp = new Help("Displays all available commands and their description.", "help");
        allCommands.put("help", commandHelp);

        Command commandMap = new DisplayMap("Displays the map of the game.", "map", map);
        allCommands.put("map", commandMap);

        // AUTRES COMMANDES A RAJOUTER PLUS TARD
        // Il faut créer la classe d'abord, mais vous pouvez reprendre le code en dessus
        // et changer les variables et la clef
        return allCommands;
    }

}
