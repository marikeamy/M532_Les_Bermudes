package main.Commands;
import java.util.List;

import main.Game.Inventory;
import main.Game.Item;
public class CommandInventory extends Command {

    private Inventory inventory;

    public CommandInventory(String description, String verb, Inventory inventory) {
        super(description, verb);
        this.inventory = inventory;
    }

    @Override
    public void execute(String argument) {
        List<Item> itemList = this.inventory.getItemList();
        if (!this.inventory.getItemList().isEmpty()) {
            System.out.println("You have those items in your inventory:");
            for (Item i : itemList) {
                System.out.println(i.getName());
            }
        } else {
            System.out.println("There are no items in your inventory yet!");
        }
    }

}
