package com.kamohoaliix.Objects;

import city.cs.engine.*;
import com.kamohoaliix.Values.PowerUps;
import org.jbox2d.common.Vec2;

public class PowerUp extends StaticBody {

    // Define variables for world, view, x, y, radius and player
    private World world;
    private UserView view;
    private float x;
    private float y;
    private float radius;
    private Player player;
    private PowerUps type;

    // Constructor for PowerUp class
    public PowerUp(World world, UserView view, float x, float y, float radius, Player player, PowerUps type) {
        // Pass values into superclass
        super(world, new CircleShape(radius));

        // Store the parameters into class scope variables
        this.world = world;
        this.view = view;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.player = player;
        this.type = type;

        // Set the current position of the PowerUp
        this.setPosition(new Vec2(x, y));
    }

    // Method called on collection of the powerup
    public void collect() {
        this.destroy();
    }

    // Returns the player that this powerup effects (in case of future multiplayer implementation)
    public Player getPlayer() {
        return this.player;
    }

    public float getRadius() {
        return radius;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public PowerUps getType() {
        return type;
    }
}
