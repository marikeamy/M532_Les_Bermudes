package main.Commands;

import main.Game.Game;
import main.Game.WorldMap;
import main.Game.Player;

public class Go extends Command {
    private static Go commandGo;
    private WorldMap map;
    private Player player;

    public Go(String description, String verb, WorldMap map, Player player) {
        super(description, verb);
        this.map = map;
        this.player = player;
    }

    @Override
    public void execute(String argument) {
        Game game = Game.getInstance();
        if ("north".equals(argument)) {
            if (game.getPlayer().getPlayerPosition().get(0) > 0) {
                game.getPlayer().getPlayerPosition().set(0, game.getPlayer().getPlayerPosition().get(0) - 1);
            } else {
                System.out.println("The way north is blocked.");
            }
        } else if ("south".equals(argument)) {
            if (game.getPlayer().getPlayerPosition().get(0) < 2) {
                game.getPlayer().getPlayerPosition().set(0, game.getPlayer().getPlayerPosition().get(0) + 1);
            } else {
                System.out.println("The way south is blocked.");
            }
        } else if ("west".equals(argument)  || "left".equals(argument)) {
            if (game.getPlayer().getPlayerPosition().get(1) > 0) {
                game.getPlayer().getPlayerPosition().set(1, game.getPlayer().getPlayerPosition().get(1) - 1);
            } else {
                System.out.println("The way westward is blocked.");
            }
        } else if ("east".equals(argument) || "right".equals(argument)) {
            if (game.getPlayer().getPlayerPosition().get(1) < 2) {
                game.getPlayer().getPlayerPosition().set(1, game.getPlayer().getPlayerPosition().get(1) + 1);
            } else {
                System.out.println("The way eastward is blocked.");
            }
        }
    }

}
