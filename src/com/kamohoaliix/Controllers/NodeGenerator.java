package com.kamohoaliix.Controllers;

import city.cs.engine.World;
import com.kamohoaliix.Objects.Node;

import java.util.Random;

public class NodeGenerator {
    private World world;
    private int nodeCount;
    private int colorCount;
    private int arrCount;
    private Node[] nodeArray;
    private Random rand;


    public NodeGenerator(World world) {
        this.world = world;
        this.nodeCount = 4;
        this.colorCount = 3;
        this.arrCount = this.colorCount * this.nodeCount;
        this.nodeArray = new Node[this.arrCount];
        this.rand = new Random();
    }

    private float newNodePosition() {
        return this.rand.nextFloat() * 20 - 10;
    }

    private Node newNode() {
        return new Node(this.world, this.newNodePosition(), this.newNodePosition());
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
        for(int i = this.arrCount - 1; i >= 0; i--) {
            this.nodeArray[i] = this.generateNewNode();
        }
    }

    public int getNodeCount() {
        return this.nodeCount;
    }

    public int getColorCount() {
        return this.colorCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public void setColorCount(int colorCount) {
        this.colorCount = colorCount;
    }
}
