package com.kamohoaliix.Controllers;

import city.cs.engine.World;
import com.kamohoaliix.Objects.Node;
import com.kamohoaliix.Objects.Selector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NodeClickListener extends MouseAdapter {
    private World world;
    private NodeHandler nodeHandler;
    private ConnectionHandler connectionHandler;
    private Node selectedNode;
    private Selector selector;

    public NodeClickListener(World world, NodeHandler nodeHandler, ConnectionHandler connectionHandler) {
        this.world = world;
        this.nodeHandler = nodeHandler;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean actionTaken = false;
        for (Node node : nodeHandler.getNodeArray()) {
            if (node.containsPosition(e.getPoint())) {
                if (this.selectedNode != null && this.selectedNode != node && this.selectedNode.getColor() == node.getColor()) {
                    this.connectionHandler.createConnection(this.selectedNode, node, this.selectedNode.getColor());
                    this.selector.destroy();
                    this.selectedNode = null;
                    actionTaken = true;
                } else if ((this.selectedNode != null && this.selectedNode == node) || (this.selectedNode != null && this.selectedNode != node && this.selectedNode.getColor() != node.getColor())) {
                    this.selector.destroy();
                    this.selectedNode = null;
                    actionTaken = true;
                } else {
                    this.selectedNode = node;
                    this.selector = new Selector(world);
                    this.selector.setPosition(node.getPosition());
                    actionTaken = true;
                }
            }
        }
        if(!actionTaken) {
            if(this.selector != null) {
                this.selector.destroy();
            }
            if(this.selectedNode != null) {
                this.selectedNode = null;
            }
        }
    }
}