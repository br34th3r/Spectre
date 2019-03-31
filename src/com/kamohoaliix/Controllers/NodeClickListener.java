package com.kamohoaliix.Controllers;

import city.cs.engine.World;
import com.kamohoaliix.Objects.Node;
import com.kamohoaliix.Objects.Selector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       1.0.0
 */
public class NodeClickListener extends MouseAdapter {
    /**
     * The world this object affects
     */
    private World world;
    /**
     * The NodeHandler object that handles node generation and storage
     */
    private NodeHandler nodeHandler;
    /**
     * The ConnectionHandler object that handles Connection creation and storage
     */
    private ConnectionHandler connectionHandler;
    /**
     * the currently selected node
     */
    private Node selectedNode;
    /**
     * The selector object that is to display around the selected node
     */
    private Selector selector;

    public NodeClickListener(World world, NodeHandler nodeHandler, ConnectionHandler connectionHandler) {
        this.world = world;
        this.nodeHandler = nodeHandler;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Boolean to store whether or not an action has been taken
        boolean actionTaken = false;

        // For every node, check if the node contains the point at which the mouse has been clicked
        for (Node node : nodeHandler.getNodeArray()) {
            if (node.containsPosition(e.getPoint())) {
                // If the node contains the position, check if a node has been
                // selected and it's not the node that has currently been
                // clicked as well as if the node has the same color as
                // the currently selected node
                if (this.selectedNode != null && this.selectedNode != node && this.selectedNode.getColor() == node.getColor()) {
                    // Create a connection, destroy the selector object,
                    // set selectedNode to null and express that an action has been taken
                    this.connectionHandler.createConnection(this.selectedNode, node, this.selectedNode.getColor());
                    this.selector.destroy();
                    this.selectedNode = null;
                    actionTaken = true;
                // Check to see if a node has been selected and it is the same as the node
                // that has been clicked, or if there is a selected node but it isn't
                // the same color as the node that has been clicked.
                } else if ((this.selectedNode != null && this.selectedNode == node) || (this.selectedNode != null && this.selectedNode.getColor() != node.getColor())) {
                    // Destroy the selector, set selectedNode to null and express
                    // that an action has been taken.
                    this.selector.destroy();
                    this.selectedNode = null;
                    actionTaken = true;
                } else {
                    // Otherwise set the clicked node as the selected node, create a new selector
                    // object at the node's position and express that an action has been taken
                    this.selectedNode = node;
                    this.selector = new Selector(world);
                    this.selector.setPosition(node.getPosition());
                    actionTaken = true;
                }
            }
        }
        // If after every node iteration an action has not been taken
        if(!actionTaken) {
            // If there is a selector, destroy it
            if(this.selector != null) {
                this.selector.destroy();
            }
            // If there is a selectedNode, set it to null
            if(this.selectedNode != null) {
                this.selectedNode = null;
            }
        }
    }
}