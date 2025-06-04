package main.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.List;
import utils.Color;
import utils.StringStyling;
import utils.Style;

import main.Commands.*;

public class Game {
    private static Game instance;

    private WorldMap map;
    private Player player;
    private CommandsRegistry commandsRegistry;
    private GameState gameState;
    private boolean isStarting;

    private Game(WorldMap map, Player player, CommandsRegistry commandsRegistry, GameState gameState) {
        System.out.println("Initializing game...");
        this.map = map;
        this.player = player;
        this.commandsRegistry = commandsRegistry;
        this.gameState = gameState;
        this.isStarting = true;
    }

    public static void run() {
        System.out.println("Running game...");

    }

    public static Game getInstance() {
        if (instance == null) {
            // GAME STATE
            Scanner saveScanner = new Scanner(System.in);
            GameState gameState = new GameState(new ArrayList<>());
            Inventory inventory = new Inventory(null);
            Player player = new Player("Player1", Arrays.asList(0, 1), inventory);
            WorldMap worldMap = new WorldMap(createAllLocations());
            Map<String, Command> commands = createAllCommands(worldMap, player);
            CommandsRegistry registry = new CommandsRegistry(commands);
            instance = new Game(worldMap, player, registry, gameState);
            addAllItemsToLocation();
            System.out.println("1. New game");
            System.out.println("2. Load last save");
            String choice = saveScanner.nextLine();

            if (choice.equals("2")) {
                loadGame();
            } else {
                run();
                startIntro();
            }

        }
        return instance;
    }

    public GameState getGameState() {
        return gameState;
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

    public boolean getIsStarting() {
        return isStarting;
    }

    public void setIsStarting(boolean state) {
        this.isStarting = state;
    }

    private static List<Item> createAllLetters() {
        List<Item> itemList = new ArrayList<>();
        itemList.addAll(Arrays.asList(new Letter("Broken Golden Pyramid",
                "It's a piece of a Golden Pyramid. It has a riddle inscribed on it: What has a head, a tail, but no body?",
                "coin",
                "Master Sword Meadow"),
                new Letter("Strange Toad",
                        "It's an old toad of strange shape. It eyes thee and says: it ever runs, yet never walks. It murmurs softly, but speaks no word. It holds a bed, yet knows no sleep. It has a mouth, yet taketh no food. What is it?",
                        "river", "Castle bridge"),
                new Letter("Chimney Candle",
                        "You see, inside a chimney veiled in dust, a burning candle bearing a carving upon its wax: give me food, and I shall live. Give me drink, and I shall perish. What am I?",
                        "fire", "Royal Dungeon"),
                new Letter("Curious Branch",
                        "It's a curious branch laying alone, it is shaped in the likeness of Prince Siegfried. It whispers: a long neck bear I, my gown is white - yet at whiles am I clad in black. What am I?",
                        "swan", "The Magic Lake"),
                new Letter("Blood Stained Skull",
                        "It's the skull of a servant dead long ago, it speaks to you: I drive men to madness for the love of me; I am easily beaten, yet never truly free. What am I?",
                        "gold", "Royal Throne"),
                new Item("Teleport Crystal",
                        "With this magic stone you can teleport around the world to your heart's content.")));
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
                new Location("Lost Woods",
                        "You stand in the Forgotten Woods, where twisted trees whisper secrets of a time long lost.",
                        false, new ArrayList<>()),
                new Location("Master Sword Meadow",
                        "The meadow is breaming with life. There's deers everywhere and a mighty sword plunged into a rock in the middle.",
                        true, new ArrayList<>())));

        grid.add(Arrays.asList(
                new Location("empty", null, true, null),
                new Location("Castle bridge", "You are on the Castle Bridge leading straight to the Royal Halls.",
                        true,
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
                false, new ArrayList<>()),
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
        locationGrid.get(0).get(0).getItemList().add(itemList.get(0)); // Golden Pyramid in Village
        // Code Marike
        locationGrid.get(1).get(2).getItemList().add(itemList.get(1)); // Old Toad in River
        locationGrid.get(1).get(2).getItemList().add(itemList.get(5)); // Crystal in River
        locationGrid.get(2).get(1).getItemList().add(itemList.get(2)); // Chimney candle in Castle Hall
        locationGrid.get(2).get(0).getItemList().add(itemList.get(3)); // Siegfried brand in Castle Garden
        locationGrid.get(3).get(1).getItemList().add(itemList.get(4)); // Skull in Dungeon
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

        Command commandSave = new Save("You can save the game.", "save");
        allCommands.put("save", commandSave);
        /*
         * Command commandTeleport = new Teleport(
         * "With a special item you need to find, you will be able to teleport anywhere on the map and maybe finish the game!"
         * ,
         * "teleport", map, player, player.getInventory());
         * allCommands.put("teleport", commandTeleport);
         */
        return allCommands;
    }

    public static void startIntro() {
        instance.setIsStarting(false);
        System.out.println();
        System.out.println(StringStyling.StyleString("Welcome to our game!", Style.ITALIC, Color.GREEN));
        System.out.println();

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

    public static void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader("save.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                String[] command = line.trim().split("\\s+", 2);

                if (command.length >= 1) {
                    String commandName = command[0];
                    String argument = (command.length > 1) ? command[1] : "";

                    ICommand cmd = instance.getCommandsRegistry().getCommand(commandName);
                    if (cmd != null) {
                        try {
                            cmd.execute(argument);
                            instance.getGameState().addCommand(line);
                        } catch (Exception e) {
                            System.out.println("Skipped command: " + line);
                        }
                    }
                }
            }
            System.out.println("Game loaded successfully.");
            instance.setIsStarting(false);
            run();
        } catch (IOException e) {
            System.out.println("No saved game found.");
        } catch (Exception e) {
            System.out.println("Error while loading saved game.");
            e.printStackTrace();
        }
    }
}
