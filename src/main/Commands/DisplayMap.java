package main.Commands;

import java.util.ArrayList;
import java.util.List;

import main.Game.*;
import utils.Array2Dprinter;
import utils.IPrintable;

public class DisplayMap extends Command {
    private WorldMap map;

    public DisplayMap(String description, String verb, WorldMap map) {
        super(description, verb);
        this.map = map;
    }

    @Override
    public void execute(String argument) {
        List<List<IPrintable>> toPrint = toPrintableGrid(map.getLocationGrid());
        IPrintable[][] array2D = Array2Dprinter.convert2DArray(toPrint);
        String output = Array2Dprinter.print2DArray(array2D, -1, -1);
        System.out.println(output);
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
