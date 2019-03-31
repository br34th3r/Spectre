package com.kamohoaliix.Objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Selector extends StaticBody {
    private World world;
    private AttachedImage sprite;

    // Constructor class
    public Selector(World world) {
        // Pass values into superclass
        super(world, new CircleShape(0.7f));

        // Set the world to the world passed into parameter
        this.world = world;

        // Set the sprite to the selectionCircle image stored in data
        this.sprite = new AttachedImage(this, new BodyImage("data/NODES/selectionCircle.png"), 2, 0, new Vec2(0, 0));
    }
}
