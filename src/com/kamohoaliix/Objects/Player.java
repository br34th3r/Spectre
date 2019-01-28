package com.kamohoaliix.Objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Player extends Walker {
    private AttachedImage sprite = new AttachedImage(this, new BodyImage("data/Idle__000.png", 2.5f), 1, 0, new Vec2(0, 0));

    public Player(World w, float width, float height, float x, float y) {
        super(w, new BoxShape(width, height));
        this.setPosition(new Vec2(x, y));
        this.addImage(this.sprite.getBodyImage());
    }

    @Override
    public void startWalking(float speed) {
        super.startWalking(speed);
        this.removeAllImages();
        AttachedImage img = new AttachedImage(this, new BodyImage("data/Run__000.png", 2.5f), 1, 0, new Vec2(0, 0));

        if(speed < 0) {
            img.flipHorizontal();
        }
    }

    @Override
    public void stopWalking() {
        super.stopWalking();
        this.removeAllImages();
        this.addImage(this.sprite.getBodyImage());
    }

    @Override
    public void jump(float speed) {
        super.jump(speed);
        this.removeAllImages();
        AttachedImage img = new AttachedImage(this, new BodyImage("data/Jump__000.png", 2.5f), 1, 0, new Vec2(0, 0));
    }

    public AttachedImage getIdleSprite() {
        return this.sprite;
    }
}