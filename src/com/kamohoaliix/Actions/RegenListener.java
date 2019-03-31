package com.kamohoaliix.Actions;

import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegenListener implements ActionListener {
    private CustomWorld world;
    private Player player;

    public RegenListener(CustomWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.world.reset();
        this.world.newGeneration();
        this.player.addRegen();
    }
}
