package com.kamohoaliix;

import city.cs.engine.World;
import com.kamohoaliix.Controllers.*;
import com.kamohoaliix.Environment.ControlPanel;
import com.kamohoaliix.Environment.CustomView;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Create a Player Object to Store all Player Data
        Player player = new Player();

        // Create Objects of World and CustomView Class
        CustomWorld world = new CustomWorld(60, player);

        // Create the JFrame to Display View
        JFrame frame = new JFrame("Spectre");

        // Add Listeners to Allow Interactive Functionality
        frame.addMouseListener(new NodeClickListener(world, world.getNodeHandler(), world.getConnectionHandler()));
        frame.addKeyListener(new ConnectionKeyHandler(world, world.getConnectionHandler(), world.getNodeHandler()));

        // Allow the Game to Quit on Red Button Click
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Don't Allow the Frame to Be Resizable
        frame.setResizable(false);

        // Add a New Control Panel Instance
        frame.add(new ControlPanel(world), BorderLayout.SOUTH);
        frame.pack();

        // Add the CustomView to the Frame
        frame.add(world.getView(), BorderLayout.CENTER);
        frame.pack();

        // Set the Frame to be Shown
        frame.setVisible(true);

        // Create a DebugViewer for the Frame (Can be Commented Out)
        // DebugViewer debug = new DebugViewer(world, 500, 500);

        // Start the Game
        world.start();
    }
}
