package com.kamohoaliix.Objects;

import city.cs.engine.*;
import com.kamohoaliix.Values.NodeColor;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Node extends StaticBody {
    private World world;
    private UserView view;
    private AttachedImage sprite;
    private float x;
    private float y;
    private float radius;
    private NodeColor color;

    public Node(World world, UserView view, float x, float y, float radius) {
        super(world, new CircleShape(radius));
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
}
