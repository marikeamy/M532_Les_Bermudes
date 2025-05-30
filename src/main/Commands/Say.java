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
            System.out.println("You need to say something.");
            return;
        }

        List<Item> inventory = player.getInventory().getItemList();
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof Letter) {
                Letter letter = (Letter) item;
                if (letter.isSolved()) {/*Tess */
                    continue; // On ignore les lettres déjà résolues
                }

                if (letter.checkAnswer(argument)) {/*Donne la clé*/
                    String locationName = letter.getLocationNameToUnlock();
                /*Recherche la location*/ 
                List<List<Location>> grid = map.getLocationGrid();
                boolean found = false;
                for (int y = 0; y < grid.size(); y++) {
                    List<Location> row = grid.get(y);
                    for (int x = 0; x < row.size(); x++) {
                        Location loc = row.get(x);
                        if (loc.getName().equalsIgnoreCase(locationName)) {
                            found = true;
                            if (!loc.getIsLocked()) {
                                System.out.println("This place is already unlocked. You don't need a key.");
                                return;
                            } else {
                                /*Donne la clé, marque lettre comme résolue*/
                                Item key = new Key("Key to " + locationName,
                                                  "A key that unlocks " + locationName,
                                                  locationName, map);
                                inventory.add(key);
                                letter.markAsSolved();
                                System.out.println("Correct! You've received the key to " + locationName + ".");
                                return;
                }
            }
        }
    }

if (!found) {
                    System.out.println("Error: Target location not found.");
                }
                return;
            }
        }
    }

    System.out.println("Nothing happened...");
}}
