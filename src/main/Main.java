package main;

import examples.ConsoleStylingExample;
import examples.StringManipulation;
import examples.UserInputExample;
import main.Game.Game;
import utils.Color;
import utils.StringStyling;
import utils.Style;

public class Main {

    public static void main(String[] args) {
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
    }
}
