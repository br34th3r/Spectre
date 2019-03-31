package com.kamohoaliix.Actions;

import city.cs.engine.World;
import com.kamohoaliix.Controllers.NodeHandler;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddColorListener implements ActionListener {
    private CustomWorld world;
    private Player player;
    private NodeHandler nodeGen;

    public AddColorListener(CustomWorld world, Player player, NodeHandler nodeGen) {
        this.world = world;
        this.player = player;
        this.nodeGen = nodeGen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.nodeGen.addColor();
        this.world.reset();
        this.world.resetLevels();
        this.world.newLevel();
    }
}
