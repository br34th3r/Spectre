package com.kamohoaliix.Actions;

import com.kamohoaliix.Environment.CustomWorld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       3.0.0
 */
public class VolumeUpListener implements ActionListener {
    /**
     * The world this object affects
     */
    private CustomWorld world;

    public VolumeUpListener(CustomWorld world) {
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Increase the volume exponentially by a multiplier of 1.1
        this.world.setVolume(this.world.getVolume() * 1.1);
    }
}
