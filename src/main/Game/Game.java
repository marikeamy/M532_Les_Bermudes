package main.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
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
            Inventory inventory = new Inventory(null);
            Player player = new Player("Player1", Arrays.asList(0, 1), inventory);
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

    private static List<Item> createAllLetters() {
        List<Item> itemList = new ArrayList<>();
        itemList.addAll(Arrays.asList(new Letter("Old Parchment",
                "It's an old parchment with a riddle inscribed on it: What has a head, a tail, but no body?", "coin",
                "Master Sword Meadow"),
                new Letter("Old treasure map",
                        "It's an old treasure map with a volcano drawn on it. It says: Say Merde before the great volcano and a key you shall find.",
                        "merde", "Castle floor"),
                new Letter("Strange Toad",
                        "It's an old toad of strange shape. It eyes thee and says: it ever runs, yet never walks. It murmurs softly, but speaks no word. It holds a bed, yet knows no sleep. It has a mouth, yet taketh no food. What is it?",
                        "river", "Castle bridge"),
                new Letter("Dusty Chimney",
                        "It's a chimney veiled in dust, bearing an ancient carving upon its stone: give me food, and I shall live. Give me drink, and I shall perish. What am I?",
                        "fire", "Royal Dungeon"),
                new Letter("Curious Tree",
                        "It's a curious tree standing alone, its trunk shaped in the likeness of Prince SiegFried. It whispers: a long neck bear I, my gown is white - yet at whiles am I clad in black. What am I?",
                        "swan", "The Magic Lake"),
                new Letter("Blood Stained Skeletton",
                        "It's a skeleton of a man leaning against a wall, clutching a worn notebook in his hand. Inside the notebook is a riddle: I drive men to madness for the love of me; I am easily beaten, yet never truly free. What am I?",
                        "gold", "Royal Throne"),
                new Item("Teleport Crystal",
                        "With this magic stone you can teleport around the world at your heart's content.")));

        return itemList;
    }

    private static List<List<Location>> createAllLocations() {
        List<List<Location>> grid = new ArrayList<>();
        grid.add(Arrays.asList(

                new Location("The Old Village", "The Old Village seems completely abandonned...", false,
                        new ArrayList<>()),
                new Location("The Ermit Grotto",
                        "The Grotto is now empty. You only hear the wind wispering to you.", false,
                        new ArrayList<>()),
                new Location("Forgotten Woods",
                        "You stand in the Forgotten Woods, where twisted trees whisper secrets of a time long lost.",
                        false, new ArrayList<>()),
                new Location("Master Sword Meadow",
                        "The meadow is breaming with life. There's deers everywhere and a mighty sword plunged into a rock in the middle.",
                        true, new ArrayList<>())));

        grid.add(Arrays.asList(
                new Location("empty", null, true, null),
                new Location("Castle bridge", "You are on the Castle Bridge leading straight to the Royal Halls.", true,
                        new ArrayList<>()),
                new Location("The Peaceful River",
                        "The castle floors seam endless. You might get lost if you're not careful enough.", true,
                        new ArrayList<>()),
                new Location("The Magic Lake",
                        "You are on the coast of the Magic Lake. There's some swans and cute ducks.", true,
                        new ArrayList<>())));

        grid.add(Arrays.asList(
                new Location("Castle gardens",
                        "The garden look luscious and full of life with flowers blooming everywhere.", true,
                        new ArrayList<>()),
                new Location("Castle Hall",
                        "The Castle Hall is immense and dimly lit. You hear whispers in the dark...", true,
                        new ArrayList<>()),
                new Location("empty", null, true, null),
                new Location("empty", null, true, null)));

        grid.add(Arrays.asList(new Location("Royal Throne",
                "The mighty Throne of the King stands before you, but no one's there.",
                true, new ArrayList<>()),
                new Location("Royal Dungeon",
                        "You stand in the Royal Dungeon. There's some old empty cells and blood on the walls...", true,
                        new ArrayList<>()),
                new Location("empty", null, true, null),
                new Location("Treasure Chambers",
                        "Hail, brave soul! Thou hast triumphed o’er perils untold and reached the heart of the ancient vault. Before thee lies the treasure long sought: the Elixir of Life, glowing with eternal promise, and heaps of gleaming gold beyond measure. May this reward bring thee fortune and immortality, for thou art truly a hero of legend.",
                        true,
                        new ArrayList<>())));
        // Nom et description à valider et finir.
        return grid;
    }

    private static void addAllItemsToLocation() {
        List<Item> itemList = createAllLetters();
        List<List<Location>> locationGrid = Game.getInstance().getWorldMap().getLocationGrid();
        locationGrid.get(0).get(1).getItemList().add(itemList.get(0));
        locationGrid.get(0).get(1).getItemList().add(itemList.get(1));
        // Code Marike
        locationGrid.get(1).get(2).getItemList().add(itemList.get(2));
        locationGrid.get(2).get(1).getItemList().add(itemList.get(3));
        locationGrid.get(2).get(0).getItemList().add(itemList.get(4));
        locationGrid.get(3).get(0).getItemList().add(itemList.get(5));
        locationGrid.get(0).get(1).getItemList().add(itemList.get(6)); // TELEPORT STONE
        // A finir
    }

    private static Map<String, Command> createAllCommands(WorldMap map, Player player) {
        Map<String, Command> allCommands = new TreeMap<>();

        Command commandHelp = new Help("Displays all available commands and their description.", "help");
        allCommands.put("help", commandHelp);

        Command commandGo = new Go("You can move north, west, east and south with this command.", "go", map, player);
        allCommands.put("go", commandGo);

        Command commandLook = new Look(
                "Gives you the description of your current location and displays the item inside of it.", "look", map);
        allCommands.put("look", commandLook);

        Command commandMap = new DisplayMap("Displays the map of the game.", "map", map, player);
        allCommands.put("map", commandMap);

        Command commandTake = new Take("Take an object found in the location you are in.", "take", map, player);
        allCommands.put("take", commandTake);

        Command commandSay = new Say("Say a word to answer a riddle.", "say", map, player);
        allCommands.put("say", commandSay); /* Tess */

        Command commandUse = new Use("Use an object you found.", "use", map, player);
        allCommands.put("use", commandUse); /* Tess */

        Command commandInspect = new Inspect("You can inspect whichever item you possess and find what their use are.",
                "inspect", player.getInventory());
        allCommands.put("inspect", commandInspect);

        Command commandInventory = new CommandInventory("You can inspect whatever item is in your inventory.",
                "inventory", player.getInventory());
        allCommands.put("inventory", commandInventory);

        Command commandTeleport = new Teleport(
                "With a special item you need to find, you will be able to teleport anywhere on the map and maybe finish the game!",
                "teleport", map, player, player.getInventory());
        allCommands.put("teleport", commandTeleport);

        return allCommands;
    }

    public static void startIntro() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("An old man stands before you. He says:");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "\"The King is dead and you're the only heir still alive.\"");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "\"Find the secret numbers scattered around the world to claim the treasures of the king as yours.\"");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The old man laughs and disappears in a puff of smoke.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Game.getInstance().getWorldMap().getPlayerLocation().getDescription());
    }
}
