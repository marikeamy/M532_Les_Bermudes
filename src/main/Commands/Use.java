package main.Commands;

import java.util.List;

import main.Game.Item;
import main.Game.Key;
import main.Game.Letter;
import main.Game.Player;

public class Use extends Command {
    private Player player;

    public Use(String description, String verb, Player player) {
        super(description, verb);
        this.player = player;
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
            boolean isAdjacentSuccess = key.playerIsAdjacentToLocation(key);
            boolean unlockSuccess = key.unlockLocation(key);
            if (isAdjacentSuccess && unlockSuccess) {
                inventory.remove(itemToUse);
                printOutput("You unlocked the " + key.getLocationNameToUnlock() + ".");
            } else if (!isAdjacentSuccess) {
                printOutput("You have to be next to the location you want to unlock to use your key!");
            } else {
                printOutput("The key doesn't match any locked location.");
            }
        } else if (itemToUse instanceof Letter) {
            Letter letter = (Letter) itemToUse;
            if (letter.isSolved()) {
                printOutput("You already solved the riddle: " + letter.getDescription());
            } else {
                printOutput(letter.getDescription());
            }
        } else {
            printOutput("This item can't be used.");
        }
    }
}
