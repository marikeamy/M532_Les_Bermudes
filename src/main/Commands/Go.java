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

    @Override
    public void execute(String argument) {
        int positionY = player.getPlayerPosition().get(0);
        int positionX = player.getPlayerPosition().get(1);
        List<List<Location>> grid = map.getLocationGrid();
        if ("north".equals(argument)) {
            if (positionY > 0) {
                List<Location> rowAbove = grid.get(positionY - 1);
                if (positionX >= 0 && positionX < rowAbove.size() && !rowAbove.get(positionX).getIsLocked()) {
                    player.getPlayerPosition().set(0, positionY - 1);
                    System.out.println("You went north and arrived at the " + map.getPlayerLocation().getName());
                } else {
                    System.out.println("The path north to the " + map.getPlayerLocation().getName() + " is locked.");
                }
            } else {
                System.out.println("The path north is completely blocked.");
            }
        } else if ("south".equals(argument)) {
            if (positionX < 2) {
                List<Location> rowAbove = grid.get(positionY + 1);
                if (positionX >= 0 && positionX < rowAbove.size() && !rowAbove.get(positionX).getIsLocked()) {
                    player.getPlayerPosition().set(0, positionY + 01);
                    System.out.println("You went south and arrived at the " + map.getPlayerLocation().getName() + ".");
                } else {
                    System.out.println("The path south to the " + map.getPlayerLocation().getName() + " is locked.");
                }
            } else {
                System.out.println("The path south is completely blocked.");
            }
        } else if ("west".equals(argument) || "left".equals(argument)) {
            if (positionX > 0) {
                if (!grid.get(positionY).get(positionX - 1).getIsLocked()) {
                    player.getPlayerPosition().set(1, positionX - 1);
                    System.out.println("You went west and arrived at the " + map.getPlayerLocation().getName() + ".");
                } else {
                    System.out.println("The path westward to the " + map.getPlayerLocation().getName() + " is locked.");
                }
            } else {
                System.out.println("The path westward is completely blocked.");
            }
        } else if ("east".equals(argument) || "right".equals(argument)) {
            if (positionX + 1 < grid.get(positionY).size()) {
                if (!grid.get(positionY).get(positionX + 1).getIsLocked()) {
                    player.getPlayerPosition().set(1, positionX + 1);
                    System.out.println("You went east and arrived at the " + map.getPlayerLocation().getName() + ".");
                } else {
                    System.out.println("The path eastward to the " + map.getPlayerLocation().getName() + " is locked.");
                }
            } else {
                System.out.println("The path eastward is completely blocked.");
            }
        } else {
            System.out.println("I don't recognise this direction.");
        }
    }
}
