package ui;

import model.Item;

import javax.swing.*;
import java.util.ArrayList;

public class ListItemsManager {
    private static final String[] columnNames = {"ID", "NAME", "STOCK", "ROP"};
    private ArrayList<Item> itemList;

    public ListItemsManager(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public JTable createTable() {
        Object[][] data = itemListToData();
        return new JTable(data, columnNames);
    }

    private Object[][] itemListToData() {
        Object[][] data = new Object[itemList.size()][columnNames.length];
        for (int i = 0; i < itemList.size(); i++) {
            data[i] = itemToArray(itemList.get(i));
        }
        return data;
    }

    private Object[] itemToArray(Item item) {
        Object[] result = new Object[4];
        result[0] = item.getId();
        result[1] = item.getName();
        result[2] = item.getStock();
        result[3] = item.getReorderPoint();
        return result;
    }
}