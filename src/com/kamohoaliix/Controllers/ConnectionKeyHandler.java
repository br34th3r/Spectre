package com.kamohoaliix.Controllers;

import city.cs.engine.World;
import com.kamohoaliix.Objects.Connection;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       1.0.0
 */
public class ConnectionKeyHandler extends KeyAdapter {
    /**
     * The world this object affects
     */
    private World world;
    /**
     * The ConnectionHandler object that handles Connection creation and storage
     */
    private ConnectionHandler connectionHandler;
    /**
     * The NodeHandler object that handles node generation and storage
     */
    private NodeHandler nodeHandler;

    public ConnectionKeyHandler(World world, ConnectionHandler connectionHandler, NodeHandler nodeHandler) {
        this.world = world;
        this.connectionHandler = connectionHandler;
        this.nodeHandler = nodeHandler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Get the keyboard keyCode
        int code = e.getKeyCode();

        // If the code represents backspace
        if(code == KeyEvent.VK_BACK_SPACE) {
            // Pop the last connection, remove it and then
            // remove the connection on it's nodes and
            // finally destroy it
            Connection removing = this.connectionHandler.removeLastNode();
            removing.removeNodeConnections();
            removing.destroy();
        }
    }

}
