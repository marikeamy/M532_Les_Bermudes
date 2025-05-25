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

            String[] command = sentence.split("\\s+");
            if (command.length > 2) {
                System.out.println("Try to write one to two words.");
            } else if (command.length == 2) {
                try {
                    Game.getInstance().getCommandsRegistry().getCommand(command[0]).execute(command[1]);
                } catch (Exception e) {
                    System.out.println("I don't recognise this command.");
                }

            } else if (command.length == 1) {
                try {
                    Game.getInstance().getCommandsRegistry().getCommand(command[0]).execute(null);
                } catch (Exception e) {
                    System.out.println("I don't recognise this command.");
                }
            } else if (command[0].isEmpty()) {
                System.out.println("Try to write a command.");
            }
        }
    }

}
