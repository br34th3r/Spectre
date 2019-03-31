package com.kamohoaliix.Actions;

import com.kamohoaliix.Controllers.NodeHandler;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       3.0.0
 */
public class AddColorListener implements ActionListener {
    /**
     * The world this object affects
     */
    private CustomWorld world;
    /**
     * The player this object affects
     */
    private Player player;
    /**
     * The NodeHandler object that handles node generation and storage
     */
    private NodeHandler nodeGen;

    public AddColorListener(CustomWorld world, Player player, NodeHandler nodeGen) {
        this.world = world;
        this.player = player;
        this.nodeGen = nodeGen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Add a color to the NodeHandler then reset the level
        this.nodeGen.addColor();
        this.world.reset();
        this.world.resetLevels();
        this.world.newLevel();
    }
}
