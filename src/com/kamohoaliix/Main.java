package com.kamohoaliix;

import city.cs.engine.DebugViewer;
import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Create a New Game World
	    World world = new World();
	    Random rand = new Random();

	    // Initialise the Screen that the User Can See
        UserView view = new UserView(world, 500, 500);

        // Create a New Player
        Player player = new Player(100);

        // Generate New Level
        GenerateLevel levelGen = new GenerateLevel(world, 5, 3);

        // Initialise the Frame to Contain the View
        JFrame frame = new JFrame("Spectre");

        // Adjust Frame Settings for Display
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setVisible(true);

        // Start the Game
        world.start();

        // OPTIONAL : Debug View
        JFrame debugView = new DebugViewer(world, 500, 500);
    }
}
