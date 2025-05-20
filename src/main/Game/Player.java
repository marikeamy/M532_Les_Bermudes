package main.Game;

import java.util.List;

public class Player {
    private String name;
    private List<Integer> playerPosition;

    public Player(String name, List<Integer> playerPosition) {
        this.name = name;
        this.playerPosition = playerPosition;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPlayerPosition() {
        return playerPosition;
    }
}
