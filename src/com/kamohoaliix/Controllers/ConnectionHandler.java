package com.kamohoaliix.Controllers;
import city.cs.engine.World;
import com.kamohoaliix.Environment.CustomView;
import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Connection;
import com.kamohoaliix.Objects.Node;
import com.kamohoaliix.Objects.Player;
import com.kamohoaliix.Objects.PowerUp;
import com.kamohoaliix.Values.NodeColor;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.util.Stack;

import static java.lang.Math.abs;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       1.0.0
 */
public class ConnectionHandler {
    /**
     * The world this object affects
     */
    private CustomWorld world;
    /**
     * The custom view object in which the world is displayed
     */
    private CustomView view;
    /**
     * The NodeHandler object that handles node generation and storage
     */
    private NodeHandler nodeHandler;
    /**
     * The PowerUpHandler object that handles PowerUp generation and storage
     */
    private PowerUpHandler powerUpGen;
    /**
     * The first connected node
     */
    private Node firstNode;
    /**
     * The second connected node
     */
    private Node secondNode;
    /**
     * The player this object affects
     */
    private Player player;
    /**
     * The stack of connections that have been created
     */
    private Stack<Connection> connections;
    /**
     * the total number of connections that have been created
     */
    private int connectionCount;

    /**
     * Takes and stores all necessary objects for the calculations and recursive algorithms to make sure no intersections occur on generation of the objects.
     * @param world a CustomWorld object that the ConnectionHandler will exist within.
     * @param nodeHandler the NodeHandler currently active in the same world, storing the Nodes that have been or will be generated on the screen.
     * @param powerUpGen the PowerUpHandler currently active in the same world, storing the PowerUps that have been or will be generated on the screen.
     * @param player the current Player and all data associated with that player, to change score or number of regenerations.
     * @param view the current view that the world exists within.
     */
    public ConnectionHandler(CustomWorld world, NodeHandler nodeHandler, PowerUpHandler powerUpGen, Player player, CustomView view) {
        this.world = world;
        this.view = view;
        this.nodeHandler = nodeHandler;
        this.powerUpGen = powerUpGen;
        this.player = player;
        this.connections = new Stack<Connection>();
    }

    /**
     * Creates a connection between two nodes and supplies that connection with a color based on the two nodes.
     * @param firstNode the first node to have been selected by the player to create a connection between.
     * @param secondNode the second node to have been selected by the player to create a connection between.
     * @param color the color of the two nodes to be passed on to the connection.
     */
    public void createConnection(Node firstNode, Node secondNode, NodeColor color) {
        // Set the firstNode and secondNode values appropriately
        this.firstNode = firstNode;
        this.secondNode = secondNode;

        // Add a connection to each of the nodes
        this.firstNode.addConnection();
        this.secondNode.addConnection();

        // Increase the total number of connections by one
        this.connectionCount += 1;

        // Create a new Connection object
        Connection newConn = new Connection(this.world, this.getPointsDistance(), 0.1f, this.findCenterPos(), (float) this.convertAngleForPositions(this.findAngle()), firstNode, secondNode, color);

        // Check if any PowerUps intersect the Connection and pick them up if they do
        for(PowerUp checkPowerUp : this.powerUpGen.getPowerUpArray()) {
            if(checkPowerUp != null && checkPowerUp.intersects(newConn)) {
                checkPowerUp.collect();
            }
        }

        // Check if the connection intersects any other connections
        // and decrement the score by 50 for every intersection
        for(Connection connection : this.connections) {
            if(connection.intersects(newConn) && connection.getColor() != newConn.getColor()) {
                player.changeScore(-50);
            }
        }

        // Push the new connection to the connection stack
        this.connections.push(newConn);

        // Check if the level is complete and proceed to new level if it is
        if(this.isLevelComplete()) {
            this.world.newLevel();
        }

        // Check if the player is dead
        if(this.player.isDead()) {

            // If the player is dead, display an appropriate prompt
            // and reset the world for a new game
            JOptionPane.showMessageDialog(this.view, "You Lose!");
            this.world.reset();
            this.world.newLevel();
            this.player.resetRegens();
            this.player.resetScore();
            this.world.resetLevels();
        }
    }

    /**
     * Calculates the x and y position of the central point between two nodes.
     * @return a Vec2() object containing the central point between the two nodes.
     */
    private Vec2 findCenterPos() {
        // Find the x and y values of the center position by doing the
        // two x and y values of the nodes added together then divide both by 2
        float x = (this.firstNode.getPosition().x + this.secondNode.getPosition().x) / 2;
        float y = (this.firstNode.getPosition().y + this.secondNode.getPosition().y) / 2;

        // Create a new Vec2 object based on the results and return it
        return new Vec2(x, y);
    }

    /**
     * Returns the angle between two nodes using pythagorean theorem and Java's Math library.
     * @return a double representation of the angle in degrees
     */
    private double findAngle() {
        // Calculate the difference between the x and y values
        float xDiff = abs(this.firstNode.getPosition().x - this.secondNode.getPosition().x);
        float yDiff = abs(this.firstNode.getPosition().y - this.secondNode.getPosition().y);

        // Divide them based on Pythagorean theorem (SOHCAHTOA)
        double oppOverAdj = yDiff / xDiff;

        // Use Java's Math library to run atan and calculate the angle
        double angle = Math.atan(oppOverAdj);

        // Return the angle
        return angle;
    }

    /**
     * Returns the float distance (CityEngine Game Meters) between two Nodes stored in firstNode and secondNode fields.
     * @return float value of the distance in game meters.
     */
    private float getPointsDistance() {
        // Calculate the difference between both the x and y values of the two points and square them
        double xDiff = Math.pow((this.secondNode.getPosition().x - this.firstNode.getPosition().x), 2);
        double yDiff = Math.pow((this.secondNode.getPosition().y - this.firstNode.getPosition().y), 2);

        // Return the float cast square roots of the two differences divided by 2
        return (float) Math.sqrt(xDiff + yDiff) / 2;
    }

    /**
     * Converts the angle form the findAngle() function based on the relative positions of the two nodes and in which quadrants they are in relative to one another.
     * @param angle the angle to be converted based on the positions of the currently stored first and second nodes.
     * @return a double representation of the angle that has been modified to represent accurately based on the relative node positions.
     */
    private double convertAngleForPositions(double angle) {
        // Check if the first node's x position is less than the second node's
        if(this.firstNode.getPosition().x < this.secondNode.getPosition().x) {
            // If so, check if the first node's y position is greater than the second node's
            if(this.firstNode.getPosition().y > this.secondNode.getPosition().y) {
                // Return pi take away the angle
                return Math.PI - angle;
            } else {
                // Return just the angle
                return angle;
            }
        } else {
            // Otherwise, check if the first node's y value is greater than the second node's
            if(this.firstNode.getPosition().y > this.secondNode.getPosition().y) {
                // Return pi plus the angle
                return Math.PI + angle;
            } else {
                // Return just the negative angle
                return -angle;
            }
        }
    }

    /**
     * Clears the connection stack by popping and then destroying each object individually, also returns the connectionCount variable to 0.
     */
    public void clearConnections() {
        // iterate through the connections stack and destroy each connection
        for(Connection conn : this.connections) {
            conn.destroy();
        }

        // Clear the connections stack
        this.connections.clear();

        // Reset the connection count to 0
        this.connectionCount = 0;
    }

    /**
     * Used to destroy the previously made connection.
     * @return the connection to have just been removed from the stack.
     */
    public Connection removeLastNode() {
        return this.connections.pop();
    }

    /**
     * Returns true if every node has at least one connection to another node, proving that the level is indeed completed
     * @return boolean value, true if the level is completed and false if not.
     */
    public boolean isLevelComplete() {
        // Assume all nodes are connected
        boolean allConnected = true;

        // Iterate through all the nodes and see if one doesn't have a connection
        for(Node node : this.nodeHandler.getNodeArray()) {
            if(!node.isConnected()) {
                // If so, set the allConnected value to false
                allConnected = false;
            }
        }
        // If all nodes are connected after check then return true, otherwise false
        if(allConnected) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the connections stack.
     * @return stack of Connection objects that are currently stored inside the ConnectionHandler object.
     */
    public Stack<Connection> getConnections() {
        return this.connections;
    }

    /**
     * Returns the current number of connections that have been created.
     * @return the number of connections that have been created.
     */
    public int getConnectionCount() {
        return this.connectionCount;
    }
}
