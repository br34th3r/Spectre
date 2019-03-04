package com.kamohoaliix.Objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Selector extends StaticBody {
    private World world;
    private AttachedImage sprite;

    public Selector(World world) {
        super(world, new CircleShape(0.7f));
        this.world = world;
        this.sprite = new AttachedImage(this, new BodyImage("data/NODES/selectionCircle.png"), 2, 0, new Vec2(0, 0));
    }
}
