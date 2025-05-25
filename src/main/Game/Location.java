
package main.Game;

import java.util.List;

public class Location {
    private String name;
    private String description;
    private boolean isLocked;
    private List<Item> itemList;

    // Constructeur de location
    public Location(String name, String description, boolean isLocked, List<Item> itemList) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.itemList = itemList;
    }

    //Getters
    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean getIsLocked(){
        return this.isLocked;
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    //Methods
    public void unlock() {
    }

}
