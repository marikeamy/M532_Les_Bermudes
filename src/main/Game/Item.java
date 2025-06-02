package main.Game;

public class Item {
    private String name; // Ou protected?
    private String description; // Ou protected?

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

}
