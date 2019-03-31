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

public class ExtraPoints extends PowerUp {
    private int[] pointsOptions = {50, 100, 150, 200};
    private int points;
    private AttachedImage sprite;

    public ExtraPoints(World world, UserView view, float x, float y, float radius, Player player) {
        super(world, view, x, y, radius, player, PowerUps.points);
        this.calculatePoints();
        this.sprite = new AttachedImage(this, new BodyImage("data/Powerup/powerup.png"), 1, 0, new Vec2(0, 0));
    }

    public void calculatePoints() {
        int index = ThreadLocalRandom.current().nextInt(0, 4);
        this.points = this.pointsOptions[index];
    }

    @Override
    public void collect() {
        this.getPlayer().changeScore(this.points);
        this.destroy();
    }
}
