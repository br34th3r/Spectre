package com.kamohoaliix.Environment;

import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Player;

import javax.swing.*;
import java.awt.*;

public class CustomView extends UserView {
    private Image image = new ImageIcon("data/bg.jpg").getImage();
    private Player player;

    public CustomView(World w, int width, int height, Player player) {
        super(w, width, height);
        this.player = player;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(this.image, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.drawString("Score : " + this.player.getScore(), 10, 20);
    }
}
