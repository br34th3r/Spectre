package com.kamohoaliix.Environment;

import com.kamohoaliix.Actions.*;
import com.kamohoaliix.Controllers.NodeHandler;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class ControlPanel extends JPanel implements ChangeListener {
    // Create fields for each button and the volume JPanel
    /**
     * Regen Button
     */
    private JButton regenButton;
    /**
     * Quit Button
     */
    private JButton quitButton;
    /**
     * Add Color Button
     */
    private JButton addColorButton;
    /**
     * Remove Color Button
     */
    private JButton removeColorButton;
    /**
     * Save Button
     */
    private JButton saveButton;
    /**
     * Load Button
     */
    private JButton loadButton;
    /**
     * Volume Panel
     */
    private JPanel volumePanel;
    /**
     * Volume Up Button
     */
    private JButton volumeUp;
    /**
     * Volume Down Button
     */
    private JButton volumeDown;

    /**
     * Create all the buttons and panel objects and add them to the ControlPanel to be added to the right side of the view.
     * @param world the world that the buttons will affect.
     * @param player the player that the buttons affect.
     * @param nodeHandler the node handler object that the buttons will affect.
     */
    public ControlPanel(CustomWorld world, Player player, NodeHandler nodeHandler) {
        super(new GridLayout(7, 1));

        //Create the add color button and add the appropriate listener
        this.addColorButton = new JButton("Add Color");
        this.addColorButton.addActionListener(new AddColorListener(world, player, nodeHandler));

        //Create the remove color button and add the appropriate listener
        this.removeColorButton = new JButton("Remove Color");
        this.removeColorButton.addActionListener(new RemoveColorListener(world, player, nodeHandler));

        //Create the quit button and add the appropriate listener
        this.quitButton = new JButton("Quit");
        this.quitButton.addActionListener(new QuitAction());

        //Create the regen button and add the appropriate listener
        this.regenButton = new JButton("Regenerate");
        this.regenButton.addActionListener(new RegenListener(world, player));

        //Create the save button and add the appropriate listener
        this.saveButton = new JButton("Save Current State");
        this.saveButton.addActionListener(new SaveListener(world, player));

        //Create the load button and add the appropriate listener
        this.loadButton = new JButton("Load State from File");
        this.loadButton.addActionListener(new LoadListener(world, player));

        //Create the volume panel and buttons and add appropriate listeners
        // with a grid layout to display the buttons neatly
        this.volumePanel = new JPanel(new GridLayout(1, 2));
        this.volumeUp = new JButton("Sound +");
        this.volumeUp.addActionListener(new VolumeUpListener(world));
        this.volumeDown = new JButton("Sound -");
        this.volumeDown.addActionListener(new VolumeDownListener(world));
        this.volumePanel.add(this.volumeUp).setFocusable(false);
        this.volumePanel.add(this.volumeDown).setFocusable(false);

        // Add all buttons and the volume panel to the view and make
        // sure keyboard events still work within the game window
        this.add(this.addColorButton).setFocusable(false);
        this.add(this.removeColorButton).setFocusable(false);
        this.add(this.regenButton).setFocusable(false);
        this.add(this.volumePanel).setFocusable(false);
        this.add(this.saveButton).setFocusable(false);
        this.add(this.loadButton).setFocusable(false);
        this.add(this.quitButton).setFocusable(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // If the state of the control panel changes then repaint it
        this.repaint();
    }
}
