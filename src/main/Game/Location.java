
package main.Game;

import java.util.List;

import utils.Color;
import utils.IPrintable;
import utils.StringStyling;
import utils.Style;

public class Location implements IPrintable {
    private String name;
    private String description;
    private boolean isLocked;
    private boolean wasVisited;
    private List<Item> itemList;

    // Constructeur de location
    public Location(String name, String description, boolean isLocked, boolean wasVisited, List<Item> itemList) {
        this.name = name;
        this.description = description;
        this.isLocked = isLocked;
        this.wasVisited = wasVisited;
        this.itemList = itemList;
    }

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

    public boolean getWasVisited() {
        return this.wasVisited;
    }

    public void setWasVisited(Boolean change) {
        this.wasVisited = change;
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    // Methods
    public void unlock() {
        this.isLocked = false;
    }

    @Override
    public String getPrintableString() {
        if (!isLocked) {
            return name;
        }
        return "";
    }

    @Override
    public boolean isGrayedOut() {
        return isLocked;
    }

    @Override
    public boolean wasVisited() {
        return wasVisited;
    }

}
