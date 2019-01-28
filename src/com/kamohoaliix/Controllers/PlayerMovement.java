package com.kamohoaliix.Controllers;

import com.kamohoaliix.Objects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerMovement extends KeyAdapter {
    private Player player;
    private float playerSpeed = 6f;

    public PlayerMovement(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_D) {
            this.player.startWalking(this.playerSpeed);
        } else if(code == KeyEvent.VK_A) {
            this.player.startWalking(-this.playerSpeed);
        } else if(code == KeyEvent.VK_W) {
            this.player.jump(this.playerSpeed);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_A) {
            this.player.stopWalking();
        }
    }
}
