package com.kamohoaliix.Actions;

import com.kamohoaliix.Controllers.SaveLoadController;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       3.0.0
 */
public class LoadListener implements ActionListener {
    /**
     * The world this object affects
     */
    private CustomWorld world;
    /**
     * The player this object affects
     */
    private Player player;

    public LoadListener(CustomWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Open the file chooser for the user to select their save file
        JFileChooser loadDialog = new JFileChooser();

        // Filter the allowed files the user is able to select
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
        loadDialog.setFileFilter(filter);

        // Store whether or not the selected file is acceptable
        int returnVal = loadDialog.showOpenDialog(this.world.getView());
        if(returnVal == JFileChooser.APPROVE_OPTION) {

            // If the return file is valid, create a new saveLoad
            // controller and load the file
            String fileName = loadDialog.getSelectedFile().getAbsolutePath();
            SaveLoadController saveLoad = new SaveLoadController(this.world, this.player);
            saveLoad.loadFile(fileName);
        }
    }
}
