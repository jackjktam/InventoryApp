package ui;

import exceptions.DuplicateIdException;
import exceptions.NegativeAmountException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddItemManager {

    private JTextField idField;
    private JTextField nameField;
    private JTextField stockField;
    private JTextField ropField;

    public AddItemManager() {
        idField = new JTextField("", 4);
        nameField = new JTextField("", 4);
        stockField = new JTextField("", 4);
        ropField = new JTextField("", 4);
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getStockField() {
        return stockField;
    }

    public JTextField getRopField() {
        return ropField;
    }

    public void populateAddItemPanel(JPanel panel) {
        panel.add(new JLabel("ID: "));
        panel.add(idField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("NAME: "));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("STOCK: "));
        panel.add(stockField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("ROP: "));
        panel.add(ropField);
    }
}
