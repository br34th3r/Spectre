package com.kamohoaliix.Objects;

import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import com.kamohoaliix.Values.NodeColor;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       1.0.0
 */
public class Connection extends StaticBody {
    /**
     * The world this object affects
     */
    private World world;
    /**
     * Connection width
     */
    private float width;
    /**
     * Connection height
     */
    private float height;
    /**
     * Connection central position
     */
    private Vec2 centerPos;
    /**
     * Connection angle
     */
    private float angle;
    /**
     * The nodes that this connection connects
     */
    private Node[] connectedNodes;
    /**
     * The color of the connection based on the nodes it connects
     */
    private NodeColor color;

    /**
     * Constructor to store all necessary values about the connection and run the handleColor() function.
     * @param world world in which the connection is displayed.
     * @param width width of the connection.
     * @param height height of the connection.
     * @param centerPos the centerPosition of the connection rectangle.
     * @param angle the angle at which the rectangle is.
     * @param node1 the first connected node.
     * @param node2 the second connected node.
     * @param color the color of the connection taken from the two connecting nodes.
     */
    public Connection(World world, float width, float height, Vec2 centerPos, float angle, Node node1, Node node2, NodeColor color) {
        super(world, new BoxShape(width, height, centerPos, angle));
        this.world = world;
        this.width = width;
        this.height = height;
        this.centerPos = centerPos;
        this.angle = angle;
        this.connectedNodes = new Node[]{node1, node2};
        this.color = color;
        this.handleColor(color);
    }

    /**
     * Switch statement takes the NodeColor enum values and assigns the fill color to the connection rectangle.
     * @param color NodeColor enum value.
     */
    private void handleColor(NodeColor color) {
        // Takes the enum value and assigns different fill colors based on the value.
        switch(color) {
            case red:
                this.setFillColor(Color.red);
                break;
            case blue:
                this.setFillColor(Color.blue);
                break;
            case green:
                this.setFillColor(Color.green);
                break;
            case orange:
                this.setFillColor(Color.orange);
                break;
            case purple:
                this.setFillColor(Color.magenta);
                break;
            case yellow:
                this.setFillColor(Color.yellow);
                break;
            default:
                break;
        }
    }

    /**
     * Method to remove a connection from each node (normally upon destroying the connection)
     */
    public void removeNodeConnections() {
        // Takes the nodes inside the connectedNodes array and removes one connection from each of them.
        this.connectedNodes[0].removeConnection();
        this.connectedNodes[1].removeConnection();
    }

    /**
     * Getter returns the connection color.
     * @return enum value NodeColor.
     */
    public NodeColor getColor() {
        return this.color;
    }

    /**
     * Getter returns the center position of the connection's x co-ordinate.
     * @return float x position.
     */
    public float getCenterX() {
        return this.centerPos.x;
    }

    /**
     * Getter returns the center position of the connection's y co-ordinate.
     * @return float y position.
     */
    public float getCenterY() {
        return this.centerPos.y;
    }

    /**
     * Getter returns the width of the connection.
     * @return float width of connection (meters).
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Getter returns the height of the connection.
     * @return float height of connection (meters).
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Returns the array of 2 connected nodes.
     * @return Node array with 2 nodes.
     */
    public Node[] getConnectedNodes() {
        return this.connectedNodes;
    }
}
