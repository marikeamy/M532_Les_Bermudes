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
            System.out.println("You need to specify which item you want to use.");
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
            System.out.println("You don't have this item in your inventory.");
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
                        .println("You used the " + key.getName() + " to unlock " + key.getLocationNameToUnlock() + ".");
            } else {
                System.out.println("The key doesn't match any locked location.");
            }
        } else if (itemToUse instanceof Letter) {
            Letter letter = (Letter) itemToUse;
            if (letter.isSolved()) {
                System.out.println("You already solved the riddle: " + letter.getDescription());
            } else {
                System.out.println("Riddle: " + letter.getDescription());
            }
        } else {
            System.out.println("This item can't be used.");
        }
    }
}
