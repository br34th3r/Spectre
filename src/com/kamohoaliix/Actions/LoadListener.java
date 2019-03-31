package com.kamohoaliix.Actions;

import com.kamohoaliix.Controllers.SaveLoadController;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener {
    private CustomWorld world;
    private Player player;

    public LoadListener(CustomWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser loadDialog = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT Files", "txt");
        loadDialog.setFileFilter(filter);
        int returnVal = loadDialog.showOpenDialog(this.world.getView());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = loadDialog.getSelectedFile().getAbsolutePath();
            SaveLoadController saveLoad = new SaveLoadController(this.world, this.player);
            saveLoad.loadFile(fileName);
        }
    }
}
