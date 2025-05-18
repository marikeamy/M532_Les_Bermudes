package main;

public abstract class Item {
    private String name; // Ou protected?
    private String description; // Ou protected?

    // Constructeur
    public Item(String name, String descritpion) {
        this.name = name;
        this.description = description;
    }

<<<<<<< Updated upstream
    public String getName(){
        return this.name;
    }

    public String getDescription(){
=======
    public String getName() {
        return this.name;
    }

    public String getDescription() {
>>>>>>> Stashed changes
        return this.description;
    }

    public abstract void interactionsWithItem();

}
