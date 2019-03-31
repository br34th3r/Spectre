package com.kamohoaliix.Objects;

import city.cs.engine.*;
import com.kamohoaliix.Values.NodeColor;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       1.0.0
 */
public class Node extends StaticBody {
    // Define fields for world, view, sprite, x, y, radius, color and connections
    /**
     * The world this object affects
     */
    private World world;
    /**
     * The custom view object in which the world is displayed
     */
    private UserView view;
    /**
     * The sprite for this Node object
     */
    private AttachedImage sprite;
    /**
     * The node's x position
     */
    private float x;
    /**
     * The node's y position
     */
    private float y;
    /**
     * The node's radius
     */
    private float radius;
    /**
     * The node's color
     */
    private NodeColor color;
    /**
     * The number of connections that this node has
     */
    private int connections;

    /**
     * Stores the parameter values required for making
     * the node as well as passing those values into
     * the superclass and setting the Node's position.
     * @param world the world in which the Node is to be displayed.
     * @param view the view that contains the world in which the Node is to be displayed.
     * @param x the x position of the Node.
     * @param y the y position of the Node.
     * @param radius the radius of the Node object in meters.
     */
    public Node(World world, UserView view, float x, float y, float radius) {
        super(world, new CircleShape(radius));
        this.world = world;
        this.view = view;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.setPosition(new Vec2(x, y));
        this.connections = 0;
    }

    /**
     * Checks if the Node contains a certain x and y position on the screen.
     * @param point the point that the function checks if is inside the node.
     * @return boolean true if the node contains that position and false if it does not.
     */
    public boolean containsPosition(Point point) {
        // Converts the point into a readable Vec2 object that can be interpreted by the intersects() function
        Vec2 worldPosition = new Vec2(this.view.viewToWorld(point).x, this.view.viewToWorld(point).y + 1f);

        // Uses the Vec2 position to check if there is an intersection between the node and the point
        if(this.intersects(worldPosition, 0.01f, 0.01f)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Switch to handle the color of the node and assign the correct sprite.
     * @param color enum value of the current Node's color.
     */
    public void spriteColorHandler(NodeColor color) {
        // Gets the Enum Value
        switch(color) {
            // Checks in case of red and sets the sprite to the redNode.png image
            case red:
                this.setSprite(new AttachedImage(this, new BodyImage("data/NODES/redNode.png"), 1, 0, new Vec2(0, 0)));
                break;
            case blue:
                this.setSprite(new AttachedImage(this, new BodyImage("data/NODES/blueNode.png"), 1, 0, new Vec2(0, 0)));
                break;
            case green:
                this.setSprite(new AttachedImage(this, new BodyImage("data/NODES/greenNode.png"), 1, 0, new Vec2(0, 0)));
                break;
            case orange:
                this.setSprite(new AttachedImage(this, new BodyImage("data/NODES/orangeNode.png"), 1, 0, new Vec2(0, 0)));
                break;
            case purple:
                this.setSprite(new AttachedImage(this, new BodyImage("data/NODES/purpleNode.png"), 1, 0, new Vec2(0, 0)));
                break;
            case yellow:
                this.setSprite(new AttachedImage(this, new BodyImage("data/NODES/yellowNode.png"), 1, 0, new Vec2(0, 0)));
                break;
            default:
                this.removeAllImages();
                break;

        }
    }

    /**
     * Setter method to assign the sprite.
     * @param sprite the new AttachedImage object to become the new sprite.
     */
    public void setSprite(AttachedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Getter method to return the Node's color.
     * @return enum value of NodeColor
     */
    public NodeColor getColor() {
        return this.color;
    }

    /**
     * Directly sets the color of the Node and then
     * runs the spriteColorHandler method to reset
     * the Node sprite.
     * @param color the new color to set as the Node's color.
     */
    public void setColor(NodeColor color) {
        this.color = color;
        this.spriteColorHandler(color);
    }

    /**
     * add a connection this Node.
     */
    public void addConnection() {
        this.connections += 1;
    }

    /**
     * Remove a connection from this Node.
     */
    public void removeConnection() {
        this.connections -= 1;
    }

    /**
     * Check is the number of connections is greater than 0.
     * @return boolena true if there are more than 0 connections, false otherwise.
     */
    public boolean isConnected() {
        if(this.connections > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter to return the Node's x position.
     * @return float x position.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Getter to return the Node's y position.
     * @return float y position.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Getter to return the Node's radius (meters).
     * @return float Node radius (meters).
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * Getter to return the number of Node connections.
     * @return integer number of connections on the Node.
     */
    public float getConnections() {
        return this.connections;
    }
}
