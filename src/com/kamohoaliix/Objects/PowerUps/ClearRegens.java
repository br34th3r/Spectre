package com.kamohoaliix.Objects.PowerUps;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Player;
import com.kamohoaliix.Objects.PowerUp;
import com.kamohoaliix.Values.PowerUps;
import org.jbox2d.common.Vec2;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class ClearRegens extends PowerUp {
    /**
     * The sprite of the object
     */
    private AttachedImage sprite;

    /**
     * The player this object affects
     */
    private Player player;

    public ClearRegens(World world, UserView view, float x, float y, float radius, Player player) {
        super(world, view, x, y, radius, player, PowerUps.regens);
        this.player = player;
        this.sprite = new AttachedImage(this, new BodyImage("data/Powerup/powerup2.png"), 1, 0, new Vec2(0, 0));
    }

    @Override
    public void collect() {
        // Reset the player's regenerations and then destroy the PowerUp
        this.player.resetRegens();
        this.destroy();
    }
}
