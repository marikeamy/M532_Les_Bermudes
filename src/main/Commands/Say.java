package main.Commands;

import java.util.List;

import main.Game.*;

public class Say extends Command {
    private WorldMap map;
    private Player player;

    public Say(String description, String verb, WorldMap map, Player player) {
        super(description, verb);
        this.map = map;
        this.player = player;
    }

    @Override
    public void execute(String argument) {
        if (argument == null || argument.isEmpty()) {
            printOutput("You need to say something.");
            return;
        }

        List<Item> inventory = player.getInventory().getItemList();

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);

            if (item instanceof Letter) {
                Letter letter = (Letter) item;

                if (letter.isSolved()) {/* Tess */
                    continue; // On ignore les lettres déjà résolues
                }

                if (letter.checkAnswer(argument)) {/* Donne la clé */
                    String locationName = letter.getLocationNameToUnlock();
                    // Check si la location à unlock n'est pas null
                    if (locationName != null) {
                        List<List<Location>> grid = map.getLocationGrid();
                        boolean found = false;

                        for (int y = 0; y < grid.size(); y++) {
                            List<Location> row = grid.get(y);
                            for (int x = 0; x < row.size(); x++) {
                                Location loc = row.get(x);
                                if (loc.getName().equalsIgnoreCase(locationName)) {
                                    found = true;
                                    if (!loc.getIsLocked()) {
                                        printOutput("This place is already unlocked. You don't need a key.");
                                    } else {
                                        Item key = new Key("Key to " + locationName,
                                                "A key that unlocks " + locationName,
                                                locationName, map, player);
                                        inventory.add(key);
                                        printOutput("Your answer is true! You've received the key to " + locationName
                                                + ".");
                                        letter.markAsSolved();
                                    }
                                    break;
                                }
                            }
                        }

                        if (!found) {
                            printOutput("Error: Target location not found.");
                        }
                    }
                    switch (letter.getName().toLowerCase()) {
                        case "daisy petal":
                            printOutput(
                                    "You hear the wind whispering again: \"The numbers shall guide you to the treasure. In first place, you should keep the 2.\"");
                            break;
                        case "hazelnut":
                            printOutput(
                                    "A squirrel appear and steal your nut! It says before leaving: \"The numbers shall guide you to the treasure. In second place, you should keep the 5.\"");
                            break;
                        case "swan odette":
                            printOutput(
                                    "\"The swan transforms into a beautiful woman. She says : \"Thank you for saving me, my prince. The numbers shall guide you to the treasure. In third place, you should keep the 0. I hope we will meet again soon.\"");
                            printOutput("She disappears in a puff of white feathers.");
                                    break;
                        case "strange toad":
                            printOutput("The toad leaped to the Castle Bridge and lowered down the bridge. You can go inside the castle now!");
                            Game.getInstance().getWorldMap().getLocationGrid().get(1).get(1).unlock();
                            break;
                        case "curious branch":
                            printOutput(
                                    "The branch cracks and you hear a little bird sing: \"The numbers shall guide you to the treasure. In the fifth place, you should keep the 9.\"");
                            break;
                        case "queen skull":
                            printOutput(
                                    "The skull whispers: \"You truly are a worthy heir, my son. The throne of the new hall awaits you with eager patience. In the sixth place, you must keep the 3.\"");
                            break;
                        case "magic box":
                            printOutput(
                                    "The Magic Box opens let out a puff of magical clouds. You start smelling soap... Coming from... A new room..? Check your map and try to teleport there!");
                            Game.getInstance().getWorldMap().getLocationGrid().get(3).get(3).unlock();
                            break;
                        default:

                    }
                    if (!letter.isSolved()) {
                        letter.markAsSolved();
                    }
                    printOutput("The " + letter.getName() + " disappears from your inventory.");
                    inventory.remove(letter);
                    return;
                }
            }
        }

        printOutput("That doesn't seem to be the correct answer to any riddle.");
    }
}
