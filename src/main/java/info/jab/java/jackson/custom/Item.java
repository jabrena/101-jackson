package info.jab.java.jackson.custom;

public class Item {
    public Item(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
    public int id;
    public String itemName;
    public User owner;
}