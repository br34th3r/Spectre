package com.kamohoaliix;

import city.cs.engine.DebugViewer;
import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Controllers.ConnectionHandler;
import com.kamohoaliix.Controllers.NodeClickListener;
import com.kamohoaliix.Controllers.NodeHandler;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        World world = new World(60);
        UserView view = new UserView(world, 500, 500);
        NodeHandler nodeGen = new NodeHandler(world, view);
        nodeGen.newGeneration();
        ConnectionHandler connHandler = new ConnectionHandler(world, nodeGen);
        JFrame frame = new JFrame("Spectre");
        frame.addMouseListener(new NodeClickListener(world, nodeGen, connHandler));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(view);
        frame.pack();
        frame.setVisible(true);
        DebugViewer debug = new DebugViewer(world, 500, 500);
        world.start();
    }
}
