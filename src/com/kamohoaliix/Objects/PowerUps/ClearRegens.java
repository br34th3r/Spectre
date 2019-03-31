package com.kamohoaliix.Objects.PowerUps;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Player;
import com.kamohoaliix.Objects.PowerUp;
import com.kamohoaliix.Values.PowerUps;
import org.jbox2d.common.Vec2;

public class ClearRegens extends PowerUp {
    private AttachedImage sprite;
    private Player player;

    public ClearRegens(World world, UserView view, float x, float y, float radius, Player player) {
        super(world, view, x, y, radius, player, PowerUps.regens);
        this.player = player;
        this.sprite = new AttachedImage(this, new BodyImage("data/Powerup/powerup2.png"), 1, 0, new Vec2(0, 0));
    }

    @Override
    public void collect() {
        this.player.resetRegens();
        this.destroy();
    }
}
