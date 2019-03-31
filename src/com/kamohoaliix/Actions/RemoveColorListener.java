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
public class RemoveColorListener implements ActionListener {
    // Store required fields world, player and NodeHandler
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
    private NodeHandler nodeHandler;

    public RemoveColorListener(CustomWorld world, Player player, NodeHandler nodeHandler) {
        this.world = world;
        this.player = player;
        this.nodeHandler = nodeHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Remove a color and reset the level
        this.nodeHandler.removeColor();
        this.world.reset();
        this.world.resetLevels();
        this.world.newLevel();
    }
}
