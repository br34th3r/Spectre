package com.kamohoaliix.Actions;

import com.kamohoaliix.Environment.CustomWorld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VolumeDownListener implements ActionListener {
    private CustomWorld world;

    public VolumeDownListener(CustomWorld world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.world.setVolume(this.world.getVolume() * 0.9);
    }
}
