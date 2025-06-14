package main;

import main.Game.*;

public class Main {

    public static void main(String[] args) {
        boolean gameIsFinished = false;

        Game.getInstance();

        while (!gameIsFinished) {

            String sentence = Game.getInstance().getScanner().nextLine().toLowerCase();

            if (sentence.isEmpty()) {
                System.out.println("Enter a command, please. If you are lost, try typing \"help\".");
                System.out.print("> ");
                continue;
            }

            String[] command = sentence.trim().split("\s+", 2);

            try {
                String commandName = command[0];
                String argument = (command.length > 1) ? command[1] : "";

                Game.getInstance().getCommandsRegistry().getCommand(commandName).execute(argument);

                if (!commandName.equals("save")) {
                    Game.getInstance().getGameState().addCommand(sentence);
                }

            } catch (Exception e) {
                System.out.println("I don't recognise this command.");

            }
            System.out.print("> ");
        }
        Game.getInstance().getScanner().close();

    }

}
