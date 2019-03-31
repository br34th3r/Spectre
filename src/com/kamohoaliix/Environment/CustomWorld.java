package com.kamohoaliix.Environment;

import city.cs.engine.SoundClip;
import city.cs.engine.World;
import com.kamohoaliix.Controllers.ConnectionHandler;
import com.kamohoaliix.Controllers.NodeHandler;
import com.kamohoaliix.Controllers.PowerUpHandler;
import com.kamohoaliix.Objects.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class CustomWorld extends World {
    private Player player;
    private NodeHandler nodeGen;
    private PowerUpHandler powerUpGen;
    private ConnectionHandler connGen;
    private CustomView view;
    private int levelCount;
    private SoundClip gameMusic;
    private double volume;

    public CustomWorld(int fps, Player player) {
        super(fps);
        this.player = player;
        this.createView();
        this.createNewHandlers();
        this.newGeneration();
        this.levelCount = 1;
        try {
            this.gameMusic = new SoundClip("data/music/bgmusic.wav");
            this.gameMusic.loop();
            this.volume = 1;
            this.gameMusic.setVolume(this.volume);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public void createView() {
        this.view = new CustomView(this, 500, 500, this.player);
    }

    public void createNewHandlers() {
        this.nodeGen = new NodeHandler(this, this.view);
        this.powerUpGen = new PowerUpHandler(this, view, nodeGen, player);
        this.connGen = new ConnectionHandler(this, nodeGen, powerUpGen, player, this.view);
    }

    public void newGeneration() {
        nodeGen.newGeneration();
        powerUpGen.newGeneration();
    }

    public void reset() {
        this.nodeGen.resetNodeArray();
        this.powerUpGen.resetPowerUpArray();
        this.connGen.clearConnections();
    }

    public void newLevel() {
        this.levelCount += 1;
        this.reset();
        this.newGeneration();
    }

    public void resetLevels() {
        this.levelCount = 1;
    }

    public CustomView getView() {
        return this.view;
    }

    public NodeHandler getNodeHandler() {
        return this.nodeGen;
    }

    public ConnectionHandler getConnectionHandler() {
        return this.connGen;
    }

    public PowerUpHandler getPowerUpHandler() {
        return this.powerUpGen;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public SoundClip getGameMusic() {
        return this.gameMusic;
    }

    public double getVolume() {
        return this.volume;
    }

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

    public void setVolume(double vol) {
        this.volume = vol;
        this.gameMusic.setVolume(vol);
    }
}
