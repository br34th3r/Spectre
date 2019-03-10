package com.kamohoaliix.Controllers;

import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Connection;
import com.kamohoaliix.Objects.Node;
import com.kamohoaliix.Objects.PowerUp;
import com.kamohoaliix.Values.NodeColor;

import java.awt.*;
import java.util.Random;

public class NodeHandler implements PositionalGenerator {
    private World world;
    private UserView view;
    private PowerUpHandler powerUpHandler;
    private int nodesOfEachColor = 3;
    private int colorCount = 4;
    private int arrCount;
    private int currentNodesInColor = 1;
    private Node[] nodeArray;
    private int currentColor = 0;
    private Node currentNewNode;


    public NodeHandler(World world, UserView view) {
        this.world = world;
        this.view = view;
        this.arrCount = this.colorCount * this.nodesOfEachColor;
        this.nodeArray = new Node[this.arrCount];
    }


    @Override
    public float newPosition() {
        return this.rand.nextFloat() * 18 - 10;
    }

    @Override
    public Node newObject() {
        return new Node(this.world, this.view, this.newPosition(), this.newPosition(), 0.7f);
    }

    @Override
    public Node generateNewObject() {
        this.currentNewNode = this.newObject();
        for(Node checkNode : this.nodeArray) {
            if(checkNode != null && checkNode.intersects(this.currentNewNode)) {
                this.currentNewNode.destroy();
                this.currentNewNode = this.generateNewObject();
            }
        }
        return this.currentNewNode;
    }

    @Override
    public void newGeneration() {
        for(int i = this.arrCount - 1; i >= 0; i--) {
            this.nodeArray[i] = this.generateNewObject();
            this.nodeArray[i].setColor(this.getNextColor());
        }
    }

    public NodeColor getNextColor() {
        int placeHolderCNIC = this.currentNodesInColor;
        NodeColor color = NodeColor.values()[this.currentColor];
        if(placeHolderCNIC >= this.nodesOfEachColor) {
            this.currentNodesInColor = 1;
            this.currentColor++;
        } else {
            this.currentNodesInColor++;
        }
        return color;
    }

    public void resetNodeArray() {
        this.currentNodesInColor = 1;
        this.currentColor = 0;
        for(Node node : this.nodeArray) {
            if(node != null) {
                node.destroy();
            }
        }
        this.nodeArray = new Node[this.arrCount];
    }

    public Node[] getNodeArray() {
        return this.nodeArray;
    }
}
