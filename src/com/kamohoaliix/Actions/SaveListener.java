package com.kamohoaliix.Actions;

import com.kamohoaliix.Controllers.SaveLoadController;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveListener implements ActionListener {
    private CustomWorld world;
    private Player player;

    public SaveListener(CustomWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveLoadController controller = new SaveLoadController(this.world, this.player);
        try {
            controller.saveFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
