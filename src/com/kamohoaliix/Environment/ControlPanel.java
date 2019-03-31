package com.kamohoaliix.Environment;

import com.kamohoaliix.Actions.*;
import com.kamohoaliix.Controllers.NodeHandler;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ControlPanel extends JPanel implements ChangeListener {
    private JButton regenButton;
    private JButton quitButton;
    private JButton addColorButton;
    private JButton removeColorButton;
    private JButton saveButton;
    private JPanel volumePanel;
    private JButton volumeUp;
    private JButton volumeDown;

    public ControlPanel(CustomWorld world, Player player, NodeHandler nodeHandler) {
        super(new GridLayout(6, 1));
        this.addColorButton = new JButton("Add Color");
        this.addColorButton.addActionListener(new AddColorListener(world, player, nodeHandler));

        this.removeColorButton = new JButton("Remove Color");
        this.removeColorButton.addActionListener(new RemoveColorListener(world, player, nodeHandler));

        this.quitButton = new JButton("Quit");
        this.quitButton.addActionListener(new QuitAction());

        this.regenButton = new JButton("Regenerate");
        this.regenButton.addActionListener(new RegenListener(world, player));

        this.saveButton = new JButton("Save Current State");
        this.saveButton.addActionListener(new SaveListener(world, player));

        this.volumePanel = new JPanel(new GridLayout(1, 2));
        this.volumeUp = new JButton("Sound +");
        this.volumeUp.addActionListener(new VolumeUpListener(world));
        this.volumeDown = new JButton("Sound -");
        this.volumeDown.addActionListener(new VolumeDownListener(world));
        this.volumePanel.add(this.volumeUp).setFocusable(false);
        this.volumePanel.add(this.volumeDown).setFocusable(false);

        this.add(this.addColorButton).setFocusable(false);
        this.add(this.removeColorButton).setFocusable(false);
        this.add(this.regenButton).setFocusable(false);
        this.add(this.volumePanel).setFocusable(false);
        this.add(this.saveButton).setFocusable(false);
        this.add(this.quitButton).setFocusable(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.repaint();
    }
}
