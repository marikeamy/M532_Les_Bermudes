package main.Commands;

import java.util.List;

import main.Game.*;

public class Go extends Command {
    private WorldMap map;
    private Player player;

    public Go(String description, String verb, WorldMap map, Player player) {
        super(description, verb);
        this.map = map;
        this.player = player;
    }

    private void visitCurrentLocation() {
        Location current = map.getPlayerLocation();
        if (!current.getWasVisited()) {
            current.setWasVisited(true);
        }
    }

    @Override
    public void execute(String argument) {
        int positionY = player.getPlayerPosition().get(0);
        int positionX = player.getPlayerPosition().get(1);
        List<List<Location>> grid = map.getLocationGrid();

        if ("north".equals(argument) || "up".equals(argument)) {
            if (positionY > 0) {
                List<Location> rowAbove = grid.get(positionY - 1);
                if (positionX >= 0 && positionX < rowAbove.size() && !rowAbove.get(positionX).getIsLocked()) {
                    player.getPlayerPosition().set(0, positionY - 1);
                    printOutput("You went north and arrived at the " + map.getPlayerLocation().getName() + ".");
                    visitCurrentLocation();
                } else if (rowAbove.get(positionX).getIsLocked() && rowAbove.get(positionX).getName().equals("empty")) {
                    printOutput("The path north is completely blocked.");
                } else {
                    printOutput("The path north is locked. You need to find the key.");
                }
            } else {
                printOutput("The path north is completely blocked.");
            }

        } else if ("south".equals(argument) || "down".equals(argument)) {
            if (positionY + 1 < grid.size()) {
                List<Location> rowUnder = grid.get(positionY + 1);
                if (positionX >= 0 && positionX < rowUnder.size() && !rowUnder.get(positionX).getIsLocked()) {
                    player.getPlayerPosition().set(0, positionY + 1);
                    printOutput("You went south and arrived at the " + map.getPlayerLocation().getName() + ".");
                    visitCurrentLocation();
                } else if (rowUnder.get(positionX).getIsLocked() && rowUnder.get(positionX).getName().equals("empty")) {
                    printOutput("The path south is completely blocked.");
                } else {
                    printOutput("The path south is locked. You need to find the key.");
                }
            } else {
                printOutput("The path south is completely blocked.");
            }

        } else if ("west".equals(argument) || "left".equals(argument)) {
            if (positionX > 0) {
                if (!grid.get(positionY).get(positionX - 1).getIsLocked()) {
                    player.getPlayerPosition().set(1, positionX - 1);
                    printOutput("You went west and arrived at the " + map.getPlayerLocation().getName() + ".");
                    visitCurrentLocation();
                } else if (grid.get(positionY).get(positionX - 1).getIsLocked()
                        && grid.get(positionY).get(positionX - 1).getName().equals("empty")) {
                    printOutput("The path westward is completely blocked.");
                } else {
                    printOutput("The path westward is locked. You need to find the key.");
                }
            } else {
                printOutput("The path westward is completely blocked.");
            }

        } else if ("east".equals(argument) || "right".equals(argument)) {
            if (positionX + 1 < grid.get(positionY).size()) {
                if (!grid.get(positionY).get(positionX + 1).getIsLocked()) {
                    player.getPlayerPosition().set(1, positionX + 1);
                    printOutput("You went east and arrived at the " + map.getPlayerLocation().getName() + ".");
                    visitCurrentLocation();
                } else if (grid.get(positionY).get(positionX + 1).getIsLocked()
                        && grid.get(positionY).get(positionX + 1).getName().equals("empty")) {
                    printOutput("The path eastward is completely blocked.");
                } else {
                    printOutput("The path eastward is locked. You need to find the key.");
                }
            } else {
                printOutput("The path eastward is completely blocked.");
            }

        } else {
            printOutput("I don't recognise this direction.");
        }
    }
}
