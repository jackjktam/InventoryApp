package ui;

import model.Item;

import javax.swing.*;

/*
Superclass that contains implementations to display item information
 */
public class DisplayItem {

    // MODIFIES: panel
    // EFFECTS: displays a summary of the item given
    public void display(Item item) {
        JPanel panel = new JPanel();
        populateResultPanel(panel, item);
        JOptionPane.showMessageDialog(null, panel);
    }

    // MODIFIES: panel
    // EFFECTS: populates the result panel with fields and labels
    public void populateResultPanel(JPanel panel, Item item) {
        JTextField idField = new JTextField(Integer.toString(item.getId()), 4);
        JTextField nameField = new JTextField(item.getName(), 4);
        JTextField stockField = new JTextField(Integer.toString(item.getStock()), 4);
        JTextField ropField = new JTextField(Integer.toString(item.getReorderPoint()), 4);

        idField.setEditable(false);
        nameField.setEditable(false);
        stockField.setEditable(false);
        ropField.setEditable(false);

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
