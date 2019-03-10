package com.kamohoaliix.Objects;

import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import com.kamohoaliix.Values.NodeColor;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Connection extends StaticBody {
    private World world;
    private float width;
    private float height;
    private Vec2 centerPos;
    private float angle;
    private Node[] connectedNodes;
    private NodeColor color;

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

    private void handleColor(NodeColor color) {
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

    public NodeColor getColor() {
        return this.color;
    }
}
