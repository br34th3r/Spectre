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
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       1.0.0
 */
public class NodeHandler implements PositionalGenerator {
    // Create fields required for the node handler and set initial values.
    /**
     * The world this object affects
     */
    private World world;
    /**
     * The custom view object in which the world is displayed
     */
    private UserView view;
    /**
     * The PowerUpHandler object that handles PowerUp generation and storage
     */
    private PowerUpHandler powerUpHandler;
    /**
     * Number of nodes in each color group
     */
    private int nodesOfEachColor = 3;
    /**
     * The number of colors in use
     */
    private int colorCount = 4;
    /**
     * The number of nodes in each color multiplied by the total number of colors in use to get the array size
     */
    private int arrCount;
    /**
     * The counter for the number of nodes in color during a point in generation
     */
    private int currentNodesInColor = 1;
    /**
     * The node array containing all generated nodes
     */
    private Node[] nodeArray;
    /**
     * The currently used color for node generation
     */
    private int currentColor = 0;
    /**
     * The current node being tested for generation
     */
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
     * @return returns the newly generated Node object.
     */
    @Override
    public Node generateNewObject() {
        // Set the currentNewNode to a new node object
        this.currentNewNode = this.newObject();

        // If the node intersects with any other node, recursively run this method again to
        // regenerate a new node and destroy the previous one
        for(Node checkNode : this.nodeArray) {
            if(checkNode != null && checkNode.intersects(this.currentNewNode)) {
                this.currentNewNode.destroy();
                this.currentNewNode = this.generateNewObject();
            }
        }

        // If this all runs through with no intersections, return the new node
        return this.currentNewNode;
    }

    /**
     * Runs the recursive generator based on the total number of nodes required (taken from the colorCount multiplied by the number of nodes in a single color).
     */
    @Override
    public void newGeneration() {
        // For the number of colors multiplied by the number of nodes,
        // create a new node and set the color of that node
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
        // Create a placeholder value that can be edited from the currentNodesInColor value.
        int placeHolderCNIC = this.currentNodesInColor;

        // Set the current color to the currentColor position in the Enum
        NodeColor color = NodeColor.values()[this.currentColor];

        // If the placeholder value is greater than or equal to the number of nodes in each color.
        if(placeHolderCNIC >= this.nodesOfEachColor) {
            // Reset the value for currentNodes in color and move to the next color
            this.currentNodesInColor = 1;
            this.currentColor++;
        } else {
            // Increment the number of nodes in the color
            this.currentNodesInColor++;
        }

        // Return the color
        return color;
    }

    /**
     * Add one to the colorCount field and regenerate the number of nodes in total required to be generated.
     */
    public void addColor() {
        // If this color count is less than 6, add a color
        if(this.colorCount < 6) {
            this.colorCount += 1;
        }

        // Adjust the arrCount to accommodate during node generation
        this.arrCount = this.colorCount * this.nodesOfEachColor;
    }

    /**
     * Remove one from the colorCount field and regenerate the number of nodes in total required to be generated.
     */
    public void removeColor() {
        // If this color count is greater than 2
        if(this.colorCount > 2) {
            // Decrement the color count by 1
            this.colorCount -= 1;
        }

        // Adjust the arrCount to accommodate during node generation
        this.arrCount = this.colorCount * this.nodesOfEachColor;
    }

    /**
     * Resets the currentNodesInColor value to 1, sets the currentColor to 0 and then sequentially destory each node in the nodeArray and reset the array to empty.
     */
    public void resetNodeArray() {
        // Set the currentNodesInColor to 1
        this.currentNodesInColor = 1;

        // Set the current color to the first color
        this.currentColor = 0;

        // For every node generated, destroy it
        for(Node node : this.nodeArray) {
            if(node != null) {
                node.destroy();
            }
        }

        // Reset the node array to an empty array
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
