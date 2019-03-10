package com.kamohoaliix.Objects;

import city.cs.engine.CircleShape;
import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class PowerUp extends StaticBody {
    private World world;
    private UserView view;
    private float x;
    private float y;
    private float radius;
    private Player player;

    public PowerUp(World world, UserView view, float x, float y, float radius, Player player) {
        super(world, new CircleShape(radius));
        this.world = world;
        this.view = view;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.player = player;
        this.setPosition(new Vec2(x, y));
    }

    public void collect() {
        this.destroy();
    }

    public Player getPlayer() {
        return this.player;
    }
}
