package com.kamohoaliix.Objects;

import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Connection extends StaticBody {
    private World world;
    private float width;
    private float height;
    private Vec2 centerPos;
    private float angle;
    private Node[] connectedNodes;

    public Connection(World world, float width, float height, Vec2 centerPos, float angle, Node node1, Node node2) {
        super(world, new BoxShape(width, height, centerPos, angle));
        this.world = world;
        this.width = width;
        this.height = height;
        this.centerPos = centerPos;
        this.angle = angle;
        this.connectedNodes = new Node[]{node1, node2};
        this.setFillColor(Color.red);
    }

    public Node[] getConnectedNodes() {
        return connectedNodes;
    }

    public void setConnectedNodes(Node[] nodes) {
        this.connectedNodes = nodes;
    }
}
