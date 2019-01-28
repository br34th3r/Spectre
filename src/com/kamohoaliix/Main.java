package com.kamohoaliix;

import city.cs.engine.DebugViewer;
import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Controllers.PlayerCollision;
import com.kamohoaliix.Controllers.PlayerMovement;
import com.kamohoaliix.Objects.Ground;
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
        Player player = new Player(world, 1f, 1.1f, 0f, 0f);

        // Create the Ground
        Ground ground = new Ground(world, 40f, 1f);

        // Initialise the Frame to Contain the View
        JFrame frame = new JFrame("Spectre");

        // Adjust Frame Settings for Display
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setVisible(true);

        // Add Event Listeners
        frame.addKeyListener(new PlayerMovement(player));
        player.addCollisionListener(new PlayerCollision(player));

        // Start the Game
        world.start();

        // OPTIONAL : Debug View
        JFrame debugView = new DebugViewer(world, 500, 500);
    }
}
