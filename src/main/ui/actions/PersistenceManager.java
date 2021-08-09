package ui.actions;

import ui.InventoryInterface;
import ui.SoundManager;

import javax.swing.*;
import java.io.FileNotFoundException;

public class PersistenceManager {

    private final InventoryInterface inventoryInterface;

    public PersistenceManager(InventoryInterface inventoryInterface) {
        this.inventoryInterface = inventoryInterface;
    }

    // EFFECTS: Displays save dialog and saves inventory
    public void save() {
        try {
            inventoryInterface.save();
            JOptionPane.showMessageDialog(null, "Saved inventory");
        } catch (FileNotFoundException e) {
            SoundManager.playErrorSound();
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
            SoundManager.playErrorSound();
            JOptionPane.showMessageDialog(null, "Could not load file");
        }
    }
}
