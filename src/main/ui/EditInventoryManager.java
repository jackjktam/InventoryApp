package ui;

import model.Item;

import javax.swing.*;

public class EditInventoryManager {

    private JTextField idField;
    private JTextField amountField;

    public EditInventoryManager() {
        idField = new JTextField("", 4);
        amountField = new JTextField("", 4);
    }

    public void populateSearchField(JPanel panel) {
        panel.add(new JLabel("ID: "));
        panel.add(idField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Amount: "));
        panel.add(amountField);
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getAmountField() {
        return amountField;
    }

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
