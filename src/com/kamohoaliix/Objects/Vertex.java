package com.kamohoaliix.Objects;

import city.cs.engine.CircleShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Vertex extends StaticBody {
    public Vertex(World world, float x, float y, Color color) {
        super(world, new CircleShape(0.5f));
        this.setPosition(new Vec2(x, y));
        this.setFillColor(color);
    }
}
