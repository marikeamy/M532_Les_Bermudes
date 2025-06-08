package main.Commands;

import java.util.List;

// import main.Game.Inventory;
import main.Game.Item;
import main.Game.Key;
import main.Game.Letter;
import main.Game.Player;
import main.Game.WorldMap;

public class Use extends Command {
    private WorldMap map;
    private Player player;
    /* private Inventory inventory; */

    public Use(String description, String verb, WorldMap map, Player player /* , Inventory inventory */) {
        super(description, verb);
        this.map = map;
        this.player = player;
        /*
         * this.inventory = player.getInventory(); // On éviter de passer Inventory en
         * paramètre puisqu’il est accessible via player.getInventory().
         */
    }

    @Override
    public void execute(String argument) {
        if (argument == null || argument.isEmpty()) { // Cherche dans l’inventaire un objet dont le nom correspond à
                                                      // l’argument. Si aucun objet ne correspond, message d’erreur.
            printOutput("You need to specify which item you want to use.");
            return;
        }

        List<Item> inventory = player.getInventory().getItemList();
        Item itemToUse = null;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equalsIgnoreCase(argument)) {
                itemToUse = inventory.get(i);
                break;
            }
        }

        if (itemToUse == null) {
            printOutput("You don't have this item in your inventory.");
            return;
        }

        // Si l’objet est une clé, on déverrouille l’endroit actuel via
        // map.getPlayerLocation().unlock() on supprime l’objet de l’inventaire et
        // affiche un message.
        if (itemToUse instanceof Key) {
            Key key = (Key) itemToUse;
            boolean success = key.unlockLocation();
            if (success) {
                inventory.remove(itemToUse);
                System.out
                        .println("You unlocked " + key.getLocationNameToUnlock() + ".");
            } else {
                printOutput("The key doesn't match any locked location.");
            }
        } else if (itemToUse instanceof Letter) {
            Letter letter = (Letter) itemToUse;
            if (letter.isSolved()) {
                printOutput("You already solved the riddle: " + letter.getDescription());
            } else {
                printOutput("Riddle: " + letter.getDescription());
            }
        } else {
            printOutput("This item can't be used.");
        }
    }
}
