package com.kamohoaliix.Controllers;

import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import com.kamohoaliix.Objects.Ground;
import com.kamohoaliix.Objects.Player;

public class PlayerCollision implements CollisionListener {
    private Player player;

    public PlayerCollision(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Ground) {
            player.removeAllImages();
            player.addImage(player.getIdleSprite().getBodyImage());
        }
    }
}
