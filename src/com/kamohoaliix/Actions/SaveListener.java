package com.kamohoaliix.Actions;

import com.kamohoaliix.Controllers.SaveLoadController;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       3.0.0
 */
public class SaveListener implements ActionListener {
    // Add required fields world and player
    /**
     * The world this object affects
     */
    private CustomWorld world;
    /**
     * The player this object affects
     */
    private Player player;

    public SaveListener(CustomWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a new SaveLoadController for the level
        SaveLoadController controller = new SaveLoadController(this.world, this.player);
        try {
            // Try to save the file with the current date
            // and display that it is completed
            controller.saveFile();
            JOptionPane.showMessageDialog(this.world.getView(), "Save Complete!");
        } catch (IOException e1) {
            // If there is an error, display the error
            e1.printStackTrace();
        }
    }
}
