package com.kamohoaliix.Objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Node extends StaticBody {
    private World world;
    private UserView view;
    private AttachedImage sprite;
    private float x;
    private float y;
    private float radius;

    public Node(World world, UserView view, float x, float y, float radius) {
        super(world, new CircleShape(radius));
        this.sprite = new AttachedImage(this, new BodyImage("data/NODES/node.png"), 2, 0, new Vec2(0, 0));
        this.world = world;
        this.view = view;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.setPosition(new Vec2(x, y));
    }

    public boolean containsPosition(Point point) {
        Vec2 worldPosition = new Vec2(this.view.viewToWorld(point).x, this.view.viewToWorld(point).y + 1f);
        if(this.intersects(worldPosition, 0.01f, 0.01f)) {
            return true;
        } else {
            return false;
        }
    }

    public void setSprite(AttachedImage sprite) {
        this.sprite = sprite;
    }

    public void deselect() {
        this.removeAllImages();
        this.setSprite(new AttachedImage(this, new BodyImage("data/NODES/node.png"), 2, 0, new Vec2(0, 0)));
    }

    public void select() {
        this.removeAllImages();
        this.setSprite(new AttachedImage(this, new BodyImage("data/NODES/selectedNode.png"), 2, 0, new Vec2(0, 0)));
    }
}
