package main;

public abstract class Item {
    private String name; //Ou protected?
    private String description; //Ou protected?

    //Constructeur
    public Item(String name, String descritpion){
        this.name=name;
        this.description=description;
    }

    public String getName(){}

    public String getDescription(){}

    public abstract void interactionsWithItem();

}
