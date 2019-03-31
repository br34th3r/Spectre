package com.kamohoaliix.Actions;

import com.kamohoaliix.Controllers.NodeHandler;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveColorListener implements ActionListener {
    private CustomWorld world;
    private Player player;
    private NodeHandler nodeHandler;

    public RemoveColorListener(CustomWorld world, Player player, NodeHandler nodeHandler) {
        this.world = world;
        this.player = player;
        this.nodeHandler = nodeHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.nodeHandler.removeColor();
        this.world.reset();
        this.world.resetLevels();
        this.world.newLevel();
    }
}
