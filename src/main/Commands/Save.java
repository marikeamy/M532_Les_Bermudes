package main.Commands;

import java.io.*;
import main.Game.*;

public class Save extends Command {

    public Save(String description, String verb) {
        super(description, verb);
    }

    @Override
    public void execute(String argument) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"))) {
            for (String command : Game.getInstance().getGameState().getCommandHistory()) {
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
