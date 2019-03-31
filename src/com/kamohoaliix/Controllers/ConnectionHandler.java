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
 * Handles all Connection Object Creation and Mathematics to Render and Store Connections between the different Node Objects.
 */
public class ConnectionHandler {
    private CustomWorld world;
    private CustomView view;
    private NodeHandler nodeHandler;
    private PowerUpHandler powerUpGen;
    private Node firstNode;
    private Node secondNode;
    private Player player;
    private Stack<Connection> connections;
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
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.firstNode.addConnection();
        this.secondNode.addConnection();
        this.connectionCount += 1;
        Connection newConn = new Connection(this.world, this.getPointsDistance(), 0.1f, this.findCenterPos(), (float) this.convertAngleForPositions(this.findAngle()), firstNode, secondNode, color);
        for(PowerUp checkPowerUp : this.powerUpGen.getPowerUpArray()) {
            if(checkPowerUp != null && checkPowerUp.intersects(newConn)) {
                checkPowerUp.collect();
            }
        }
        for(Connection connection : this.connections) {
            if(connection.intersects(newConn) && connection.getColor() != newConn.getColor()) {
                player.changeScore(-50);
            }
        }
        this.connections.push(newConn);
        if(this.isLevelComplete()) {
            this.world.newLevel();
        }
        if(this.player.isDead()) {
            JOptionPane.showMessageDialog(this.view, "Your Score Got Too Low! You Lose");
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
        float x = (this.firstNode.getPosition().x + this.secondNode.getPosition().x) / 2;
        float y = (this.firstNode.getPosition().y + this.secondNode.getPosition().y) / 2;
        return new Vec2(x, y);
    }

    /**
     * Returns the angle between two nodes using pythagorean theorem and Java's Math library.
     * @return a double representation of the angle in degrees
     */
    private double findAngle() {
        float xDiff = abs(this.firstNode.getPosition().x - this.secondNode.getPosition().x);
        float yDiff = abs(this.firstNode.getPosition().y - this.secondNode.getPosition().y);
        double oppOverAdj = yDiff / xDiff;
        double angle = Math.atan(oppOverAdj);
        return angle;
    }

    /**
     * Returns the float distance (CityEngine Game Meters) between two Nodes stored in firstNode and secondNode fields.
     * @return float value of the distance in game meters.
     */
    private float getPointsDistance() {
        double xDiff = Math.pow((this.secondNode.getPosition().x - this.firstNode.getPosition().x), 2);
        double yDiff = Math.pow((this.secondNode.getPosition().y - this.firstNode.getPosition().y), 2);
        return (float) Math.sqrt(xDiff + yDiff) / 2;
    }

    /**
     * Converts the angle form the findAngle() function based on the relative positions of the two nodes and in which quadrants they are in relative to one another.
     * @param angle the angle to be converted based on the positions of the currently stored first and second nodes.
     * @return a double representation of the angle that has been modified to represent accurately based on the relative node positions.
     */
    private double convertAngleForPositions(double angle) {
        if(this.firstNode.getPosition().x < this.secondNode.getPosition().x) {
            if(this.firstNode.getPosition().y > this.secondNode.getPosition().y) {
                return Math.PI - angle;
            } else {
                return angle;
            }
        } else {
            if(this.firstNode.getPosition().y > this.secondNode.getPosition().y) {
                return Math.PI + angle;
            } else {
                return -angle;
            }
        }
    }

    /**
     * Clears the connection stack by popping and then destroying each object individually, also returns the connectionCount variable to 0.
     */
    public void clearConnections() {
        for(Connection conn : this.connections) {
            conn.destroy();
        }
        this.connections.clear();
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
        boolean allConnected = true;
        for(Node node : this.nodeHandler.getNodeArray()) {
            if(!node.isConnected()) {
                allConnected = false;
            }
        }
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
