package com.kamohoaliix.Environment;

import city.cs.engine.UserView;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import java.awt.*;

public class CustomView extends UserView {
    private Image image = new ImageIcon("data/bg.png").getImage();
    private Player player;
    private CustomWorld world;

    public CustomView(CustomWorld world, int width, int height, Player player) {
        super(world, width, height);
        this.player = player;
        this.world = world;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(this.image, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.drawString("Score : " + this.player.getScore(), 10, 20);
        g.drawString("Number of Regenerations : " + this.player.getRegens(), 10, 40);
        g.drawString("Level : " + this.world.getLevelCount(), 10, 60);
    }
}
