package main.Commands;

import java.util.List;
import java.util.Map;

import main.Game.Inventory;
import main.Game.Item;
import main.Game.Player;
import main.Game.WorldMap;

public class Use extends Command{
    private WorldMap map;
    private Player player;
    private Inventory inventory;

public Use (String description, String verb, WorldMap map, Player player, Inventory inventory) {
        super(description, verb);
        this.map = map;
        this.player = player;
        this.inventory = inventory;
    }
/*Ne fonctionne pas Tess*/
/*Pour utiliser les Items */
    @Override
    public void execute(String argument) {
        List<Item> itemList = inventory.getItemList();
        System.out.println(inventory.getDescription());
        if (!itemList.isEmpty()) {
            System.out.println("Here is you're item list");
            for (Item i : itemList) {
                System.out.println(i.getName());
            }
        }
    }
}
