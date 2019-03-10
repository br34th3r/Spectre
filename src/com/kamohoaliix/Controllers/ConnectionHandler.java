package com.kamohoaliix.Controllers;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Connection;
import com.kamohoaliix.Objects.Node;
import com.kamohoaliix.Objects.Player;
import com.kamohoaliix.Objects.PowerUp;
import com.kamohoaliix.Values.NodeColor;
import org.jbox2d.common.Vec2;

import java.util.Stack;

import static java.lang.Math.abs;

public class ConnectionHandler {
    private World world;
    private NodeHandler nodeHandler;
    private PowerUpHandler powerUpGen;
    private Node firstNode;
    private Node secondNode;
    private Player player;
    private Stack<Connection> connections;

    public ConnectionHandler(World world, NodeHandler nodeHandler, PowerUpHandler powerUpGen, Player player) {
        this.world = world;
        this.nodeHandler = nodeHandler;
        this.powerUpGen = powerUpGen;
        this.player = player;
        this.connections = new Stack<Connection>();
    }

    public void createConnection(Node firstNode, Node secondNode, NodeColor color) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
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

    public void clearConnections() {
        this.connections.clear();
    }

    public void removeLastNode() {
        Connection toBeRemoved = this.connections.pop();
        toBeRemoved.destroy();
    }

}
