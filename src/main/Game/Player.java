package main.Game;

import java.util.List;

public class Player {
    private String name;
    private List<Integer> playerPosition;
    private Inventory inventory;

    public Player(String name, List<Integer> playerPosition, Inventory inventory) {
        this.name = name;
        this.playerPosition = playerPosition;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPlayerPosition() {
        return playerPosition;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
