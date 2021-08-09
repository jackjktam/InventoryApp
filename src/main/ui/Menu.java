package ui;

import model.Inventory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    //Where the GUI is created:

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    ActionManager actionManager;

    public Menu(ActionManager actionManager) {
        this.actionManager = actionManager;
        createMenu();
    }

    public void createMenu() {
        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("Save inventory");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load inventory from file");
        menuItem.addActionListener(this);
        menu.add(menuItem);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        actionManager.handleMenu(menuItem);
    }
}
