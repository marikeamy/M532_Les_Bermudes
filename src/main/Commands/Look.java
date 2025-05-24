
import main.Game.*;

/*Tess 24.05.25*/

public class Look extends Command {
    private WorldMap map;
    private Player player;

    public Look(String description, String verb, WorldMap map, Player player) {
        super(description, verb);
        this.map = map;
        this.player = player;
    }

    @Override
    public void execute(String argument) {
        Location currentLocation = map.getPlayerLocation();
        System.out.println(currentLocation.getDescription());
    }
}