package ui;

import model.Inventory;
import model.Item;

import javax.swing.*;

// Helper class for finding items
public class FindItemManager {

    private JTextField searchIdField;

    // MODIFIES: panel
    // EFFECTS: populates the search panel with fields and labels
    public void populateSearchPanel(JPanel panel) {
        searchIdField = new JTextField("", 4);
        panel.add(searchIdField);
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

    public int getSearchIdField() {
        return Integer.parseInt(searchIdField.getText());
    }
}
