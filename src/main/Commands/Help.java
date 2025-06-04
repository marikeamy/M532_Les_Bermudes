package main.Commands;

import main.Game.Game;

import java.util.Map;

/*Tess 25.05.25*/
public class Help extends Command {

    public Help(String description, String verb) {
        super(description, verb);
    }

    @Override
    public void execute(String argument) {
        Map<String, Command> allCommands = Game.getInstance().getCommandsRegistry().getCommandsRegistry();
        printOutput("Available commands:");
        for (Command cmd : allCommands.values()) {
            printOutput("- " + cmd.getVerb() + ": " + cmd.getDescription());
        }
    }
}
