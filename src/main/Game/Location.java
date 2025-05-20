
package main.Game;

import java.util.List;

public class Location implements IPrintable {
    private String name;
    private String description;
    private boolean isLocked;
    private List<Item> itemList;

    // Constructeur de location
    public Location(String name, String description, boolean locked) {
        this.name = name;
        this.description = description;
        this.isLocked = locked;
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsLocked() {
        return this.isLocked;
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    //Methods
    public void unlock() {
    }

    @Override
    public String getPrintableString() {
        //affichera le nom de la zone.
        return this.getName();
    }

    @Override
    public boolean isGrayedOut() { //en gris ou en normal ?
        return false;
    }
}
