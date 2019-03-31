package com.kamohoaliix.Objects;

import city.cs.engine.*;
import com.kamohoaliix.Values.NodeColor;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Node class that renders an individual node on the screen
 * and handles it's color, position and connections.
 */
public class Node extends StaticBody {
    // Define fields for world, view, sprite, x, y, radius, color and connections
    private World world;
    private UserView view;
    private AttachedImage sprite;
    private float x;
    private float y;
    private float radius;
    private NodeColor color;
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
        switch(color) {
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

    public NodeColor getColor() {
        return this.color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
        this.spriteColorHandler(color);
    }

    public void addConnection() {
        this.connections += 1;
    }

    public void removeConnection() {
        this.connections -= 1;
    }

    public boolean isConnected() {
        if(this.connections > 0) {
            return true;
        } else {
            return false;
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getRadius() {
        return this.radius;
    }

    public float getConnections() {
        return this.connections;
    }
}
