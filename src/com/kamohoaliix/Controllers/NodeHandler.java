package com.kamohoaliix.Controllers;

import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Connection;
import com.kamohoaliix.Objects.Node;

import java.awt.*;
import java.util.Random;

public class NodeHandler {
    private World world;
    private UserView view;
    private int nodeCount;
    private int colorCount;
    private int arrCount;
    private int connectionCount;
    private Connection[] connections;
    private Node[] nodeArray;
    private Random rand;
    private int currentColor;
    private String[] colors = {"RED", "GREEN", "BLUE", "PURPLE", "ORANGE", "YELLOW"};


    public NodeHandler(World world, UserView view) {
        this.world = world;
        this.view = view;
        this.nodeCount = 3;
        this.colorCount = 4;
        this.arrCount = this.colorCount * this.nodeCount;
        this.nodeArray = new Node[this.arrCount];
        this.connectionCount = this.arrCount - this.colorCount;
        this.rand = new Random();
    }

    private float newNodePosition() {
        return this.rand.nextFloat() * 20 - 10;
    }

    private Node newNode() {
        return new Node(this.world, this.view, this.newNodePosition(), this.newNodePosition(), 0.7f, this.colors[this.currentColor]);
    }

    private Node generateNewNode() {
        Node newNode = this.newNode();
        for(Node checkNode : this.nodeArray) {
            if(checkNode != null && checkNode.intersects(newNode)) {
                newNode.destroy();
                newNode = this.generateNewNode();
            }
        }
        return newNode;
    }

    public void newGeneration() {
        int nodesOfColor = 0;
        for(int i = this.arrCount - 1; i >= 0; i--) {
            this.nodeArray[i] = this.generateNewNode();
            nodesOfColor = nodesOfColor + 1;
            if(nodesOfColor == this.colorCount) {
                nodesOfColor = 0;
                this.currentColor = this.currentColor + 1;
            }
        }
        for(Node node : nodeArray) {
            System.out.println(node.color);
        }
    }

    public int getNodeCount() {
        return this.nodeCount;
    }

    public int getColorCount() {
        return this.colorCount;
    }

    public int getArrCount() {
        return this.arrCount;
    }

    public Node[] getNodeArray() {
        return this.nodeArray;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }

    public void setArrCount(int arrCount) {
        this.arrCount = arrCount;
    }
}
