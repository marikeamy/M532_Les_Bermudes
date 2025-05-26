package main.Commands;

import java.util.ArrayList;
import java.util.List;

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
        List<List<IPrintable>> toPrint = toPrintableGrid(map.getLocationGrid());
        IPrintable[][] array2D = Array2Dprinter.convert2DArray(toPrint);
        String inputString = Array2Dprinter.print2DArray(array2D, player.getPlayerPosition().get(0), player.getPlayerPosition().get(1));
        System.out.println(inputString);
    }

    public static List<List<IPrintable>> toPrintableGrid(List<List<Location>> grid) {
        List<List<IPrintable>> printableGrid = new ArrayList<>();
        for (List<Location> row : grid) {
            List<IPrintable> printableRow = new ArrayList<>();
            printableRow.addAll(row);
            printableGrid.add(printableRow);
        }
        return printableGrid;
    }
}
