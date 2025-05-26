package main.Game;

public class Key extends Item{
    
    //clé doit être reliée à Location.
    //clé doit porter le nom de Location qu'elle ouvre.
    //lui donenr la worldMap
    WorldMap map;
    Player player;

    public Key (String name, String description, WorldMap map, Player player){
        super(name, description);
        this.map = map;
        this.player = player;
    }

    @Override
    public void interactionsWithItem() {
        throw new UnsupportedOperationException("Unimplemented method 'interactionsWithItem'");
    }
}
