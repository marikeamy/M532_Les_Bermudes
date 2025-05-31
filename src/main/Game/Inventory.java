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

    // Uniquement lorsque le joueur récupère un item ? 
    // Ou est-ce que des items tombent dans l'inventaire suite à la résolution d'une énigme par ex?
    public void addItem() {
        
    }

    // Lors de l'utilisation de l'Item
    public void deleteItem() {
    }

}
