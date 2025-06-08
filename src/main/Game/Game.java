package main.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
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
    private Scanner inputScanner;

    private Game(WorldMap map, Player player, CommandsRegistry commandsRegistry, GameState gameState) {
        System.out.println("Initializing game...");
        this.map = map;
        this.player = player;
        this.commandsRegistry = commandsRegistry;
        this.gameState = gameState;
        this.isStarting = true;
        this.inputScanner = new Scanner(System.in);
    }

    public static void run() {
        System.out.println("Running game...");

    }

    public static Game getInstance() {
        if (instance == null) {
            GameState gameState = new GameState(new ArrayList<>());
            Inventory inventory = new Inventory(null);
            Player player = new Player("Player1", Arrays.asList(0, 1), inventory);
            WorldMap worldMap = new WorldMap(createAllLocations());
            CommandsRegistry registry = new CommandsRegistry(createAllCommands(worldMap, player));
            instance = new Game(worldMap, player, registry, gameState);
            addAllItemsToLocation();
            titleScreen();
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

    public Scanner getScanner() {
        return inputScanner;
    }

    private static List<Item> createAllLetters() {
        List<Item> itemList = new ArrayList<>();
        itemList.addAll(Arrays.asList(new Letter("Broken Golden Pyramid",
                "It's a piece of a Golden Pyramid. It has a riddle inscribed on it: What has a head, a tail, but no body?",
                "coin",
                "Master Sword Meadow"),
                new Letter("Strange Toad",
                        "The toad whispers in your ear : \"What is always coming but never arrives?\"",
                        "tomorrow", null),
                new Letter("Chimney Candle",
                        "You see, inside a chimney veiled in dust, a burning candle bearing a carving upon its wax: give me food, and I shall live. Give me drink, and I shall perish. What am I?",
                        "fire", "Royal Dungeon"),
                new Letter("Curious Branch",
                        "It's a curious branch laying alone, it is shaped in the likeness of Prince Siegfried. It whispers: \"A long neck bear I, my gown is white - yet at whiles am I clad in black. What am I?\"",
                        "swan", "Magic Lake"),
                new Letter("Queen Skull",
                        "It's the skull of the Queen dead long ago, it speaks to you: \"I drive men to madness for the love of me; I am easily beaten, yet never truly free. What am I?\"",
                        "gold", "Royal Throne"),
                new Item("Teleport Crystal",
                        "With this magic stone you can teleport around the world to your heart's content."),
                new Letter("Magic Box",
                        "The box is bearing an ancient carving : The numbers shall reveal the path to eternity...",
                        "250693", null),
                new Letter("Daisy petal", /* Tess 07.06.25 */
                        "The petal in your hand, the wind whispers in your ears: \"I have no voice, but I can tell you many stories. I have no legs, but I can spread for miles. I am full of life, but I am not alive. What am I?\"",
                        "woods", null),
                new Letter("Hazelnut", /* Tess 07.06.25 */
                        "On the hazelnut is carved: I gather nuts but have no hands. I climb trees but have no feet. I have a bushy tail and love to leap. What am I?",
                        "squirrel", null),
                new Letter("Master Sword", /* Tess 07.06.25 */
                        "On the Sword is engraved a message: I have a mouth but never speak. I have a bed but never sleep. I can run, but I have no legs. What am I?",
                        "river", "Peaceful River"),
                new Letter("Swan Odette", /* Tess 07.06.25 */
                        "The swan tells you: \"By day, I am bound by a cruel spell, A prisoner of a fate I didn't compel. But when darkness falls and the stars ignite, A broken promise might end my endless night.\" \n\"Only true love's vow, and a heart so pure, Can free me from the form I must endure. Who is the one destined to save me, my only hope?\"",
                        "siegfried", null)));
        return itemList;

    }

    private static List<List<Location>> createAllLocations() {
        List<List<Location>> grid = new ArrayList<>();
        grid.add(Arrays.asList(

                new Location("Old Village", "The Old Village seems completely abandonned...", false, false,
                        new ArrayList<>()),
                new Location("Ermit Grotto",
                        "The Grotto is now empty. You only hear the wind wispering to you.", false, true,
                        new ArrayList<>()),
                new Location("Lost Woods",
                        "You stand in the Forgotten Woods, where twisted trees whisper secrets of a time long lost.",
                        false, false, new ArrayList<>()),
                new Location("Master Sword Meadow",
                        "The meadow is breaming with life. There's deers everywhere and a mighty sword plunged into a rock in the middle.",
                        true, false, new ArrayList<>())));

        grid.add(Arrays.asList(
                new Location("empty", null, true, false, null),
                new Location("Castle bridge",
                        "You are on the Castle Bridge leading straight to the Royal Halls. You see written on the doors: in the fourth place, you should keep the 6.",
                        true, false,
                        new ArrayList<>()),
                new Location("Peaceful River",
                        "It's a peaceful river with some ducks, plants and a strange toad standing on a rock.", true,
                        false,
                        new ArrayList<>()),
                new Location("Magic Lake",
                        "You are on the coast of the Magic Lake. There's some swans and cute ducks.", true, false,
                        new ArrayList<>())));

        grid.add(Arrays.asList(
                new Location("Castle gardens",
                        "The garden look luscious and full of life with flowers blooming everywhere.", false, false,
                        new ArrayList<>()),
                new Location("Castle Hall",
                        "The Castle Hall is immense and dimly lit. You hear whispers in the dark...", false, false,
                        new ArrayList<>()),
                new Location("empty", null, true, false, null),
                new Location("empty", null, true, false, null)));

        grid.add(Arrays.asList(
                new Location("Royal Throne",
                        "The mighty Throne of the Queen stands before you, but no one's there.", true,
                        false, new ArrayList<>()),
                new Location("Royal Dungeon",
                        "You stand in the Royal Dungeon. There's some old empty cells and blood on the walls...", true,
                        false,
                        new ArrayList<>()),
                new Location("empty", null, true, false, null),
                new Location("Hall of Bubbling Waters",
                        "",
                        true, true,
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
        locationGrid.get(2).get(0).getItemList().add(itemList.get(3)); // Siegfried branch in Castle Garden
        locationGrid.get(3).get(1).getItemList().add(itemList.get(4)); // Skull in Dungeon
        locationGrid.get(3).get(0).getItemList().add(itemList.get(6)); // Magic Box dans Royal Throne
        // Code Tess
        locationGrid.get(0).get(1).getItemList().add(itemList.get(7)); // Daisy Petal in the Ermit Grotto
        locationGrid.get(0).get(2).getItemList().add(itemList.get(8)); // Hazelnut in the Forgotten Woods
        locationGrid.get(0).get(3).getItemList().add(itemList.get(9)); // Master Sword in the Meadow
        locationGrid.get(1).get(3).getItemList().add(itemList.get(10)); // Swan Odette on the Magic Lake
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

        Command commandUse = new Use("Use an object you found.", "use", player);
        allCommands.put("use", commandUse); /* Tess */

        Command commandInspect = new Inspect("You can inspect whichever item you possess and find what their use are.",
                "inspect", player.getInventory());
        allCommands.put("inspect", commandInspect);

        Command commandInventory = new CommandInventory("You can inspect whatever item is in your inventory.",
                "inventory", player.getInventory());
        allCommands.put("inventory", commandInventory);

        Command commandSave = new Save("You can save your game and start again later.", "save");
        allCommands.put("save", commandSave);

        return allCommands;
    }

    public static void titleScreen() {
        System.out.println("1. New game");
        System.out.println("2. Load last save");
        System.out.println("Type 1 or 2 to start your game.");
        System.out.print("> ");
        boolean validChoice = false;
        int choice;

        while (!validChoice) {
            try {
                choice = Game.getInstance().getScanner().nextInt();
                if (choice == 2) {
                    File saveFile = new File("save.txt");
                    if (!saveFile.exists() || saveFile.length() == 0) {
                        System.out.println("No saves found. Starting a new game instead.");
                        validChoice = true;
                        run();
                        startIntro();
                    } else {
                        validChoice = true;
                        loadGame();
                    }
                } else if (choice == 1) {
                    validChoice = true;
                    run();
                    startIntro();
                } else {
                    System.out.println("Please write 1 or 2 to start the game.");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please write 1 or 2 to start the game.");
                Game.getInstance().getScanner().nextLine();
            }
        }
    }

    public static void startIntro() {
        instance.setIsStarting(false);
        System.out.println();
        System.out.println(StringStyling.StyleString("Welcome to Jacuzzi Quest!", Style.ITALIC, Color.GREEN));
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
                "\"The Queen is dead and you're the only heir still alive.\"");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "\"With the help of your map, try to find the secret numbers scattered around the world to claim the treasures of the Queen as yours.\"");

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
        System.out.println();
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
            System.out.println(Game.getInstance().getWorldMap().getPlayerLocation().getDescription());
            System.out.println();
        } catch (IOException e) {
            System.out.println("No saved game found.");
        } catch (Exception e) {
            System.out.println("Error while loading saved game.");
            e.printStackTrace();
        }
    }

    public static void startOutro() {
        instance.setIsStarting(false);
        System.out.println();
        System.out.println(StringStyling.StyleString("Hail, valiant soul!", Style.ITALIC, Color.GREEN));
        System.out.println();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "Thou hast braved trials most dire and ventured deep into the Queen's hidden domain - the sacred Hall of Bubbling Waters.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "Here doth the very waters bubble with enchantment, known to sages as the Elixir of Life, said to mend the flesh, ease the spirit, and turn back the sands of time.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "Let the warmth embrace thee, brave one, and let thy burdens melt into the steam.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Rest now, hero of legend, for thou art worthy indeed of such rare delights...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println(
                StringStyling.StyleString("Thank you for playing Jacuzzi Quest :)", Style.ITALIC, Color.GREEN));
    }
}
