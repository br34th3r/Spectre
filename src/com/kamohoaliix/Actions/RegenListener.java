package com.kamohoaliix.Actions;

import com.kamohoaliix.Controllers.NodeHandler;
import com.kamohoaliix.Environment.CustomWorld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegenListener implements ActionListener {
    private CustomWorld world;

    public RegenListener(CustomWorld world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.world.reset();
        this.world.newGeneration();
    }
}
