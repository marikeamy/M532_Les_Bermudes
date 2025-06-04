package main.Game;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    List<String> commandHistory = new ArrayList<>();

    public GameState(List<String> commandHistory) {
        this.commandHistory = commandHistory;
    }

    public void addCommand(String command) {
        commandHistory.add(command);
    }

    public List<String> getCommandHistory() {
        return commandHistory;
    }

    public void clearSave() {
        commandHistory.clear();
    }
}
