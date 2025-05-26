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

        System.out.println(Game.getInstance().getWorldMap().getPlayerLocation().getDescription());
        Scanner commandScanner = new Scanner(System.in);
        while (!gameIsFinished) {

            String sentence = commandScanner.nextLine().toLowerCase();

            if (sentence.isEmpty()) {
                System.out.println("Enter a command, please.");
                continue;
            }

            String[] command = sentence.trim().split("\\s+", 2);

            try {
                String commandName = command[0];
                String argument = (command.length > 1) ? command[1] : null;

                Game.getInstance().getCommandsRegistry().getCommand(commandName).execute(argument);
            } catch (Exception e) {
                System.out.println("I don't recognise this command.");
            }

        }
    }

}
