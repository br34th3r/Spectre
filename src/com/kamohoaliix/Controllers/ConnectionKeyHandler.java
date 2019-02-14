package com.kamohoaliix.Controllers;

import city.cs.engine.World;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConnectionKeyHandler extends KeyAdapter {
    private World world;
    private ConnectionHandler connectionHandler;
    private NodeHandler nodeHandler;

    public ConnectionKeyHandler(World world, ConnectionHandler connectionHandler, NodeHandler nodeHandler) {
        this.world = world;
        this.connectionHandler = connectionHandler;
        this.nodeHandler = nodeHandler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_BACK_SPACE) {
            this.connectionHandler.removeLastNode();
        }
    }

}
