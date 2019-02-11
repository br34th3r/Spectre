package com.kamohoaliix.Controllers;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Node;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NodeClickListener extends MouseAdapter {
    private World world;
    private NodeHandler nodeHandler;
    private ConnectionHandler connectionHandler;
    private Node selectedNode;

    public NodeClickListener(World world, NodeHandler nodeHandler, ConnectionHandler connectionHandler) {
        this.world = world;
        this.nodeHandler = nodeHandler;
        this.connectionHandler = connectionHandler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean inNode = false;
        for(Node node : nodeHandler.getNodeArray()) {
            if(node.containsPosition(e.getPoint())) {
                if(this.selectedNode != null && this.selectedNode != node) {
                    this.connectionHandler.createConnection(this.selectedNode, node);
                    this.selectedNode.deselect();
                    this.selectedNode = null;
                } else if(this.selectedNode != null && this.selectedNode == node) {
                    this.selectedNode.deselect();
                    this.selectedNode = null;
                } else {
                    this.selectedNode = node;
                    this.selectedNode.select();
                }
            }
        }
    }
}
