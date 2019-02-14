package com.kamohoaliix.Controllers;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Connection;
import com.kamohoaliix.Objects.Node;
import org.jbox2d.common.Vec2;

import java.util.Stack;

import static java.lang.Math.abs;

public class ConnectionHandler {
    private World world;
    private NodeHandler nodeHandler;
    private Node firstNode;
    private Node secondNode;
    private Stack<Connection> connections;

    public ConnectionHandler(World world, NodeHandler nodeHandler) {
        this.world = world;
        this.nodeHandler = nodeHandler;
        this.connections = new Stack<Connection>();
    }

    public void createConnection(Node firstNode, Node secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.connections.push(new Connection(this.world, this.getPointsDistance(), 0.1f, this.findCenterPos(), (float) this.convertAngleForPositions(this.findAngle()), firstNode, secondNode));
    }

    private Vec2 findCenterPos() {
        float x = (this.firstNode.getPosition().x + this.secondNode.getPosition().x) / 2;
        float y = (this.firstNode.getPosition().y + this.secondNode.getPosition().y) / 2;
        return new Vec2(x, y);
    }

    private double findAngle() {
        float xDiff = abs(this.firstNode.getPosition().x - this.secondNode.getPosition().x);
        float yDiff = abs(this.firstNode.getPosition().y - this.secondNode.getPosition().y);
        double oppOverAdj = yDiff / xDiff;
        double angle = Math.atan(oppOverAdj);
        return angle;
    }

    private float getPointsDistance() {
        double xDiff = Math.pow((this.secondNode.getPosition().x - this.firstNode.getPosition().x), 2);
        double yDiff = Math.pow((this.secondNode.getPosition().y - this.firstNode.getPosition().y), 2);
        return (float) Math.sqrt(xDiff + yDiff) / 2;
    }

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

    public void removeLastNode() {
        Connection toBeRemoved = this.connections.pop();
        toBeRemoved.destroy();
    }

}
