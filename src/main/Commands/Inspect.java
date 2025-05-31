package main.Commands;
import java.util.List;

import main.Game.*;

public class Inspect extends Command{
    private Inventory inventory;

        public Inspect (String description, String verb, Inventory inventory){
            super(description, verb);
            this.inventory = inventory;
        }

        @Override
        public void execute(String argument) {
            List<Item> itemList = inventory.getItemList();
            boolean found = false;
            // Doit donner le nom et la description de l'objet que le joueur nomme dans l'argument
            // La réponse est différente en fonction de si c'est une clé ou une lettre.
            // Si l'inventaire est vide
            if (itemList.isEmpty()) {
                System.out.println("There are no items in your inventory yet!");
            }else if(argument.isEmpty()){ //Si l'argument est vide, on affiche tous les items    
                System.out.println("You can inspect the following items in your inventory:");
                for (Item i : itemList) {
                    System.out.println("- " + i.getName() + ": " + i.getDescription());
                }
                System.out.println("Which item would you like to inspect?");
            }else{ // Sinon, on cherche l'item correspondant à l'argument
            for (Item i : itemList) {
                    if (i.getName().equalsIgnoreCase(argument.trim())) {
                        found = true;
                        if (i instanceof Key) {
                            Key key = (Key) i;
                            System.out.println("This is a key for: " + key.getLocationNameToUnlock());
                            System.out.println(key.getDescription());
                        } else if (i instanceof Letter) {
                            Letter letter = (Letter) i;
                            System.out.println(letter.getDescription());
                        } else {
                            System.out.println(i.getName() + ": " + i.getDescription());
                        }
                        break;
                    }
                }
            if (!found) {
                System.out.println("There is no such item in your inventory. Check again?");
            }   
        }
    }       
            
}