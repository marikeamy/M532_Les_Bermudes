
package main;

import java.util.List;

public class Location {
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

<<<<<<< Updated upstream
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

    public List<Item> getItemList(){
=======
    // Getters
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
>>>>>>> Stashed changes
        return this.itemList;
    }

    public void unlock() {
    }

}
