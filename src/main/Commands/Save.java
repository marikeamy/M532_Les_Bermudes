package main.Commands;

import java.io.*;
import main.Game.*;

public class Save extends Command {
    private GameState gameState;

    public Save(String description, String verb, GameState gameState) {
        super(description, verb);
        this.gameState = gameState;
    }

    @Override
    public void execute(String argument) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"))) {
            for (String command : gameState.getCommandHistory()) {
                writer.write(command);
                writer.newLine();
            }
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save game.");
            e.printStackTrace();
        }
    }
}
