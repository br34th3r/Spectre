package com.kamohoaliix.Objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Node extends StaticBody {
    private World world;
    private AttachedImage sprite;
    private float x;
    private float y;

    public Node(World world, float x, float y) {
        super(world, new CircleShape(0.7f));
        this.sprite = new AttachedImage(this, new BodyImage("data/NODES/node.png"), 2, 0, new Vec2(0, 0));
        this.world = world;
        this.x = x;
        this.y = y;
        this.setPosition(new Vec2(x, y));
    }
}
