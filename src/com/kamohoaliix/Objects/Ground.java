package com.kamohoaliix.Objects;

import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Ground extends StaticBody {
    public float width = 40f;
    public float height = 1f;
    private float x = 0f;
    private float y = -10f;

    public Ground(World w, float width, float height) {
        super(w, new BoxShape(width, height));
        this.setPosition(new Vec2(this.x, this.y));
        this.width = width;
        this.height = height;
    }
}
