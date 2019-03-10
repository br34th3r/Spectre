package com.kamohoaliix.Environment;

import com.kamohoaliix.Actions.QuitAction;
import com.kamohoaliix.Actions.RegenListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel implements ChangeListener {
    private JButton regenButton;
    private JButton quitButton;

    public ControlPanel(CustomWorld world) {
        super();
        this.quitButton = new JButton("Quit");
        this.quitButton.addActionListener(new QuitAction());
        this.regenButton = new JButton("Regenerate");
        this.regenButton.addActionListener(new RegenListener(world));
        this.add(this.regenButton);
        this.add(this.quitButton);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.repaint();
    }
}
