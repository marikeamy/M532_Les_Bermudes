package main.Commands;

import main.Game.*;
import utils.Array2Dprinter;
import utils.IPrintable;

public class DisplayMap extends Command {
    private WorldMap map;
    private Player player;

    public DisplayMap(String description, String verb, WorldMap map, Player player) {
        super(description, verb);
        this.map = map;
        this.player = player;
    }

    @Override
    public void execute(String argument) {
            IPrintable[][] array2D = Array2Dprinter.convert2DArray(map.getLocationGrid());
            String inputString = Array2Dprinter.print2DArray(array2D, player.getPlayerPosition().get(0),
                    player.getPlayerPosition().get(1));
            printOutput(inputString);
    }
}
