package com.kamohoaliix.Actions;

import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class RegenListener implements ActionListener {
    // Store fields for the world and the player
    /**
     * The world this object affects
     */
    private CustomWorld world;
    /**
     * The player this object affects
     */
    private Player player;

    /**
     * Store the parameter values in the fields for the class.
     * @param world the world the RegenListener affects.
     * @param player the player that the regeneration occurs with.
     */
    public RegenListener(CustomWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    /**
     * Override the action listener's actionPerformed() method to run code when
     * the regen button is clicked.
     * @param e the event that occurs and all information about it.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Reset the Nodes, Connections and PowerUps
        this.world.reset();
        this.world.newGeneration();
        this.player.removeRegen();

        // Check to see if the player is dead
        if(this.player.isDead()) {
            // Display dialog box if the player
            // is dead and then reset the world
            // for a new game
            JOptionPane.showMessageDialog(this.world.getView(), "You Lose!");
            this.world.reset();
            this.world.newLevel();
            this.player.resetRegens();
            this.player.resetScore();
            this.world.resetLevels();
        }
    }
}
