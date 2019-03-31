package com.kamohoaliix.Actions;

import com.kamohoaliix.Environment.CustomWorld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       3.0.0
 */
public class VolumeDownListener implements ActionListener {
    /**
     * The world this object affects
     */
    private CustomWorld world;

    public VolumeDownListener(CustomWorld world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Reduce the volume exponentially by a multiplier of 0.9
        this.world.setVolume(this.world.getVolume() * 0.9);
    }
}
