package com.kamohoaliix.Objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Class to display the selector that appears around a selected Node
 */
public class Selector extends StaticBody {
    private World world;
    private AttachedImage sprite;

    /**
     * Stores the game world that the selector will appear in
     * and attaches the correct sprite to the object.
     * @param world game world in which selector will appear
     */
    public Selector(World world) {
        // Pass values into superclass
        super(world, new CircleShape(0.7f));

        // Set the world to the world passed into parameter
        this.world = world;

        // Set the sprite to the selectionCircle image stored in data
        this.sprite = new AttachedImage(this, new BodyImage("data/NODES/selectionCircle.png"), 2, 0, new Vec2(0, 0));
    }
}
