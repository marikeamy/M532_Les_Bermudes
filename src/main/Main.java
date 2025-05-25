package main;

import examples.ConsoleStylingExample;
import examples.StringManipulation;
import examples.UserInputExample;
import main.Game.*;
import main.Commands.*;
import utils.Color;
import utils.StringStyling;
import utils.Style;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean gameIsFinished = false;
        /*
         * StringManipulation.Example();
         * ConsoleStylingExample.Example();
         * UserInputExample.Example();
         */
        // System.out.println(StringStyling.StyleString("Starting...", Style.ITALIC,
        // Color.BLACK));
        // System.out.println(StringStyling.StyleString("Terminating...", Style.ITALIC,
        // Color.BLACK));
        Game.getInstance();
        Game.run();
        // Game game = Game.getInstance();
        /*
         * System.out.println("You are " + game.getPlayer().getName() + " at position "
         * + game.getPlayer().getPlayerPosition().get(0) + ", " +
         * game.getPlayer().getPlayerPosition().get(1)
         * + ".");
         * for (List<Location> row : Game.getInstance().getWorldMap().getLocationGrid())
         * {
         * for (Location loc : row) {
         * System.out.println(loc.getName() + " - " + loc.getDescription());
         * }
         * }
         */

        // System.out.println(game.getCommandsRegistry().getCommand("Go").getVerb());

        Scanner commandScanner = new Scanner(System.in);
        while (!gameIsFinished) {
            System.out.println(Game.getInstance().getWorldMap().getPlayerLocation().getDescription());

            String sentence = commandScanner.nextLine().toLowerCase();

            if (sentence.isEmpty()) {
                System.out.println("Enter a command, please.");
                continue;
            }

            String[] command = sentence.split("\\s+");
            if (command.length > 2) {
                System.out.println("Try to write one to two words.");
            } else if (command.length == 2) {
                Game.getInstance().getCommandsRegistry().getCommand(command[0]).execute(command[1]);
            } else if (command.length == 1 || command[0].isEmpty()) {
                System.out.println("I don't recognise this sentence YET.");
            } else if (command[0].isEmpty()) {
                System.out.println("Try to write a command.");
            }
        }
    }

}
