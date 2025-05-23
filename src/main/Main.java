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
        Game game = Game.getInstance();
        /*
         * System.out.println("You are " + game.getPlayer().getName() + " at position "
         * + game.getPlayer().getPlayerPosition().get(0) + ", " +
         * game.getPlayer().getPlayerPosition().get(1)
         * + ".");
         */
        System.out.println("The player is here : " + game.getWorldMap().getPlayerLocation().getName());
        // System.out.println(game.getCommandsRegistry().getCommand("Go").getVerb());
        Scanner commandScanner = new Scanner(System.in);
        while (!gameIsFinished) {
            String sentence = commandScanner.nextLine();
            String[] command = sentence.split(" ");
            if (command.length > 2) {
                System.out.println("Try to write one to two words.");
            } else if (command.length == 2) {
                try {
                game.getCommandsRegistry().getCommand(command[0]).execute(command[1]);
                } catch(Exception e) {
                    System.out.println("I don't recognise this sentence.");
                }
            } else if(command.length == 1 && command[0].isEmpty()) {
                System.out.println("I don't recognise this sentence YET.");
            }
        }
    }

}
