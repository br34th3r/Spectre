package com.kamohoaliix.Objects;

import city.cs.engine.*;
import com.kamohoaliix.Values.PowerUps;
import org.jbox2d.common.Vec2;

/**
 * General PowerUp superclass that then is extended by the individual PowerUp objects.
 */
public class PowerUp extends StaticBody {

    // Define variables for world, view, x, y, radius and player
    private World world;
    private UserView view;
    private float x;
    private float y;
    private float radius;
    private Player player;
    private PowerUps type;

    /**
     * Creates the object based on similar parameters to a StaticBody in CityEngine.
     * @param world game world in which the PowerUp will appear
     * @param view game view that contains the game world in which the PowerUp will appear.
     * @param x the PowerUp's x co-ordinate.
     * @param y the PowerUp's y co-ordinate.
     * @param radius the radius of the full PowerUp object.
     * @param player the player this object will effect.
     * @param type the type of object that the PowerUp is.
     */
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

    /**
     * The method to be called on collecting the PowerUp.
     */
    public void collect() {
        this.destroy();
    }

    /**
     * Returns the player that this powerup effects (in case of future multiplayer implementation)
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the PowerUp object's radius in meters.
     * @return float value of the PowerUp's radius.
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Returns the x co-ordinate of the PowerUp object.
     * @return float value x co-ordinate.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y co-ordinate of the PowerUp object.
     * @return float value y co-ordinate.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the type of the PowerUp.
     * @return enum PowerUps value, either points or regens.
     */
    public PowerUps getType() {
        return type;
    }
}
