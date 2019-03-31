package com.kamohoaliix.Objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class Selector extends StaticBody {
    /**
     * World the object is contained in
     */
    private World world;

    /**
     * The sprite of the object
     */
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
