package main.Commands;

import java.util.List;

import main.Game.*;

public class Say extends Command {
    private WorldMap map;
    private Player player;
    /*
     * private Inventory inventory; Tess: pas besoin de passer l’inventaire
     * directement en paramètre.
     * Pourquoi ? Parce qu’il est déjà accessible via le Player.
     */

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
                    
                    if (locationName != null) {/* Riddle avec clé */
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
                                    return;
                                } else {
                                    /* Donne la clé, marque lettre comme résolue */
                                    Item key = new Key("Key to " + locationName,
                                            "A key that unlocks " + locationName,
                                            locationName, map);
                                    inventory.add(key);
                                    letter.markAsSolved();
                                    printOutput("Correct! You've received the key to " + locationName + ".");
                                    printOutput("The " + letter.getName() + " disappear from your inventory.");
                                    inventory.remove(letter);
                                    return;
                                }
                                }
                            }
                        }

                        if (!found) {
                            printOutput("Error: Target location not found.");
                        }

                    } else {
                        /* Riddle snas clé mais chiffre/code*/
                        letter.markAsSolved();

                        switch (letter.getName().toLowerCase()) {
                            case "a daisy petal":
                                printOutput("Your answer is true. The numbers shall guide you to the treasure. In first place, you should keep the 2.");
                                break;
                            case "hazelnut":
                                printOutput("Your answer is true. The numbers shall guide you to the treasure. In second place, you should keep the 5.");
                                break;
                            case "the swan odette":
                                printOutput("Your answer is true. The numbers shall guide you to the treasure. In third place, you should keep the 0.");
                                break;
                            case "tomorrow":
                                printOutput("Your answer is true. The way to the castle bridge is now open to you.");
                                break;
                            case "fire":
                                printOutput("Your answer is true. the old chimney creaketh open, revealing a hidden dungeon. You mayest enter.");
                                break;
                            case "swan":
                                printOutput("Your answer is true. The numbers shall guide you to the treasure. In the fifth place, you should keep the 9.");
                                break;
                            case "gold":
                                printOutput("Your answer is true. The throne of the new hall awaits you with eager patience. In the sixth place, you should keep the 3.");
                                break;
                            case "250693":
                                printOutput("Your answer is true. You start smelling bubbles... Coming from... A new room...");;
                            default:
                                printOutput("Your answer is true.");
                        }

                        printOutput("The " + letter.getName() + " disappears from your inventory.");
                        inventory.remove(letter);
                    }

                    return;
                }
            }
        }

        printOutput("That doesn't seem to be the correct answer to any riddle.");
    }
}