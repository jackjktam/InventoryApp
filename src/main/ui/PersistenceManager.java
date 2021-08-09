package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

public class PersistenceManager {

    private InventoryInterface inventoryInterface;

    public PersistenceManager(InventoryInterface inventoryInterface) {
        this.inventoryInterface = inventoryInterface;
    }

    // EFFECTS: Displays save dialog and saves inventory
    public void save() {
        try {
            inventoryInterface.save();
            JOptionPane.showMessageDialog(null, "Saved inventory");
        } catch (FileNotFoundException e) {
            SoundManager.playError();
            JOptionPane.showMessageDialog(null, "Could not save file");
        }
    }

    // MODIFIES: Inventory
    // EFFECTS: Display dialog and load inventory
    public void load() {
        try {
            inventoryInterface.load();
            JOptionPane.showMessageDialog(null, "Loaded file");
        } catch (Exception e) {
            SoundManager.playError();
            JOptionPane.showMessageDialog(null, "Could not load file");
        }
    }
}
