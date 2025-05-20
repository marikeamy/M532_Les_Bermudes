package main.Game;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> itemList;

    // Constructeur
    public Inventory(List<Item> itemList) {
        this.itemList = new ArrayList<>();
    }

    // Méthode pour récupérer la liste des objets
    public List<Item> getItemList() {
        return this.itemList;
    }

    public void addItem() {
    }

    public void deleteItem() {
    }

}
