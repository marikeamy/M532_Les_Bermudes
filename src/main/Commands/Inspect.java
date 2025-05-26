package main.Commands;
import java.util.List;

import main.Game.*;

/* marike 26.05.2025 */
//Relier à ItemList de inventaire
//Si l'objet est présent dans l'inventaire du joueur, lire sa description

public class Inspect extends Command{
    private Inventory inventory;

        public Inspect (String description, String verb, Inventory inventory){
            super(description, verb);
            this.inventory = inventory;
        }

        @Override
        public void execute(String argument) {
            List<Item> itemList = inventory.getItemList();
            //si l'argument correspond à un Item de la liste, donner le nom et la description
            if (argument.equals("key")){ //"key" ? A revoir.
                System.out.println("on va trouver l'item tkt ça prend un peu de temps");
            }else{
                System.out.println("You don't have anything of this king brave aventurer");
            }
        }
}
