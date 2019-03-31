package com.kamohoaliix.Environment;

import city.cs.engine.UserView;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import java.awt.*;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class CustomView extends UserView {
    // Add appropriate fields for the view to handle the display of data
    /**
     * Background image container
     */
    private Image image = new ImageIcon("data/bg.png").getImage();
    /**
     * The player within this view
     */
    private Player player;

    /**
     * The world this object affects
     */
    private CustomWorld world;

    public CustomView(CustomWorld world, int width, int height, Player player) {
        super(world, width, height);
        this.player = player;
        this.world = world;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        // Paint the background on to the screen
        g.drawImage(this.image, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        // Draw strings on the screen to display score, regen number and level
        g.drawString("Score : " + this.player.getScore(), 10, 20);
        g.drawString("Number of Regenerations : " + this.player.getRegens(), 10, 40);
        g.drawString("Level : " + this.world.getLevelCount(), 10, 60);
    }
}
