package model;

import exceptions.InsufficientStockException;
import exceptions.NegativeAmountException;


/*
Represents a single item within the inventory system
 */
public class Item {
    private int id;
    private int stock;
    private int reorderPoint;
    private String name;

    // REQUIRES: unique id
    // EFFECTS: creates a new item with given id and name default stock, rop: 0
    public Item(int id, String name) {
        this.name = name;
        this.id = id;
        this.stock = 0;
        this.reorderPoint = 0;
    }

    // REQUIRES: positive stock, unique id
    // EFFECTS: creates new item with given id, name and stock, default rop: 0
    public Item(int id, String name, int stock) {
        this.name = name;
        this.id = id;
        this.stock = stock;
        this.reorderPoint = 0;
    }

    // REQUIRES: positive stock, rop, unique id
    // EFFECTS: creates new item with given fields
    public Item(int id, String name, int stock, int reorderPoint) {
        this.name = name;
        this.id = id;
        this.stock = stock;
        this.reorderPoint = reorderPoint;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public int getReorderPoint() {
        return reorderPoint;
    }

    // MODIFIES: this
    // EFFECTS: decreases stock by specified amount
    public void decreaseStock(int amount) throws NegativeAmountException, InsufficientStockException {
        if (amount < 0) {
            throw new NegativeAmountException();
        }

        if (stock - amount <= 0) {
            throw new InsufficientStockException();
        }

        stock -= amount;
    }

    // EFFECTS: returns true if the item needs restocking
    public boolean needsRestock() {
        return (stock <= getReorderPoint());
    }
}
