
package main;
import java.util.List;

public class Location {
    private String name;
    private String description;
    private boolean isLocked;
    private List<Item> itemList;

    //Constructeur de location
    public Location (String name, String description, boolean locked){
    this.name=name;
    this.description=description;
    this.isLocked=locked;
    }

    //Getters
    public String getName(){}

    public String getDescription(){}

    public boolean getIsLocked(){}

    public List<Item> getItemList(){}

    public void unlock(){}

}
