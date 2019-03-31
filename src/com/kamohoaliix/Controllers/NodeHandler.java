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

/**
 * Handles all positions and recursive generation of new Nodes to make sure no nodes intersect.
 */
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

    /**
     * Takes and stores the relevant objects that are needed to generate and store the nodes effectively.
     * @param world the world in which the NodeHandler functions and generates nodes
     * @param view the view that contains the world that contains the NodeHandler object.
     */
    public NodeHandler(World world, UserView view) {
        this.world = world;
        this.view = view;
        this.arrCount = this.colorCount * this.nodesOfEachColor;
        this.nodeArray = new Node[this.arrCount];
    }

    /**
     * Overrides the newPosition() function in the superclass PositionalGenerator to edit values to produce a more specified position boundary for the nodes to generate within.
     * @return a random float with slight modifiers to be within the space of the game window.
     */
    @Override
    public float newPosition() {
        return this.rand.nextFloat() * 18 - 10;
    }

    /**
     * Overrides the newObject() function in the superclass (PositionalGenerator) to create a new Node object.
     * @return a new Node Object with values based on the further functions contained in the NodeHandler class.
     */
    @Override
    public Node newObject() {
        return new Node(this.world, this.view, this.newPosition(), this.newPosition(), 0.7f);
    }

    /**
     * The generator that runs recursively to check if the newly generated node does not intersect with any other nodes that have already been generated.
     * @return
     */
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

    /**
     * Runs the recursive generator based on the total number of nodes required (taken from the colorCount multiplied by the number of nodes in a single color).
     */
    @Override
    public void newGeneration() {
        for(int i = this.arrCount - 1; i >= 0; i--) {
            this.nodeArray[i] = this.generateNewObject();
            this.nodeArray[i].setColor(this.getNextColor());
        }
    }

    /**
     * Returns the next color in the sequence based on how many nodes of the current color have been generated successfully.
     * @return a color value for the next color in sequence.
     */
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

    /**
     * Add one to the colorCount field and regenerate the number of nodes in total required to be generated.
     */
    public void addColor() {
        if(this.colorCount < 6) {
            this.colorCount += 1;
        }
        this.arrCount = this.colorCount * this.nodesOfEachColor;
    }

    /**
     * Remove one from the colorCount field and regenerate the number of nodes in total required to be generated.
     */
    public void removeColor() {
        if(this.colorCount > 2) {
            this.colorCount -= 1;
        }
        this.arrCount = this.colorCount * this.nodesOfEachColor;
    }

    /**
     * Resets the currentNodesInColor value to 1, sets the currentColor to 0 and then sequentially destory each node in the nodeArray and reset the array to empty.
     */
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

    /**
     * Returns the array of nodes in it's current state
     * @return an array of nodes that contains all nodes on screen.
     */
    public Node[] getNodeArray() {
        return this.nodeArray;
    }

    /**
     * Returns the number of nodes in a single color.
     * @return an integer value of the number of nodes within a single color group.
     */
    public int getNodesOfEachColor() {
        return this.nodesOfEachColor;
    }

    /**
     * The number of colors currently displaying with nodes
     * @return an integer value of the number of colors on screen.
     */
    public int getColorCount() {
        return colorCount;
    }
}
