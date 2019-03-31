package com.kamohoaliix.Objects.PowerUps;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Player;
import com.kamohoaliix.Objects.PowerUp;
import com.kamohoaliix.Values.PowerUps;
import org.jbox2d.common.Vec2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class ExtraPoints extends PowerUp {
    /**
     * Array of options for the points addition
     */
    private int[] pointsOptions = {50, 100, 150, 200};
    /**
     * The finally decided number of points this PowerUp gives the user
     */
    private int points;
    /**
     * The sprite of the object
     */
    private AttachedImage sprite;

    public ExtraPoints(World world, UserView view, float x, float y, float radius, Player player) {
        super(world, view, x, y, radius, player, PowerUps.points);
        this.calculatePoints();
        this.sprite = new AttachedImage(this, new BodyImage("data/Powerup/powerup.png"), 1, 0, new Vec2(0, 0));
    }

    /**
     * Gets a random number between 0 and 3 and uses that to select the amount of
     * points to randomly give the user when picking up the PowerUp
     */
    public void calculatePoints() {
        // Get a random number between 0 and 3
        int index = ThreadLocalRandom.current().nextInt(0, 4);

        // Select the amount of points based on the random index and the array of points options
        this.points = this.pointsOptions[index];
    }

    @Override
    public void collect() {
        // Update the player's score and destroy the PowerUp
        this.getPlayer().changeScore(this.points);
        this.destroy();
    }
}
