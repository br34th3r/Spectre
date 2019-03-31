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

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class CustomWorld extends World {
    /**
     * The player this object affects
     */
    private Player player;
    /**
     * The NodeHandler object that handles node generation and storage
     */
    private NodeHandler nodeGen;
    /**
     * The PowerUpHandler object that handles PowerUp generation and storage
     */
    private PowerUpHandler powerUpGen;
    /**
     * The ConnectionHandler object that handles Connection creation and storage
     */
    private ConnectionHandler connGen;
    /**
     * The custom view object in which the world is displayed
     */
    private CustomView view;
    /**
     * The current level
     */
    private int levelCount;
    /**
     * The game music SoundClip object
     */
    private SoundClip gameMusic;
    /**
     * The current game volume
     */
    private double volume;

    /**
     * Store data and run required functions to create the view, add handlers,
     * run new generation, set the level count to 1 and add game music.
     * @param fps frames per second that the game should display
     * @param player the current player in this world
     */
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

    /**
     * Creates a new CustomView for this world.
     */
    public void createView() {
        this.view = new CustomView(this, 500, 500, this.player);
    }

    /**
     * Loads the NodeHandler, PowerUpHandler and ConnectionHandler objects into new instances.
     */
    public void createNewHandlers() {
        this.nodeGen = new NodeHandler(this, this.view);
        this.powerUpGen = new PowerUpHandler(this, view, nodeGen, player);
        this.connGen = new ConnectionHandler(this, nodeGen, powerUpGen, player, this.view);
    }

    /**
     * Creates a new generation of nodes and power ups.
     */
    public void newGeneration() {
        nodeGen.newGeneration();
        powerUpGen.newGeneration();
    }

    /**
     * Resets the entire world to a blank canvas.
     */
    public void reset() {
        this.nodeGen.resetNodeArray();
        this.powerUpGen.resetPowerUpArray();
        this.connGen.clearConnections();
    }

    /**
     * Moves on to the next level, adjusting nodes and powerups.
     */
    public void newLevel() {
        this.levelCount += 1;
        this.reset();
        this.newGeneration();
    }

    /**
     * Resets the level count to 1.
     */
    public void resetLevels() {
        this.levelCount = 1;
    }

    /**
     * Getter returns the view that displays this world.
     * @return CustomView object of world's view
     */
    public CustomView getView() {
        return this.view;
    }

    /**
     * Getter returns the NodeHandler object stored in this world.
     * @return NodeHandler object for this world.
     */
    public NodeHandler getNodeHandler() {
        return this.nodeGen;
    }

    /**
     * Getter returns the ConnectionHandler object stored in this world.
     * @return ConnectionHandlder object for this world.
     */
    public ConnectionHandler getConnectionHandler() {
        return this.connGen;
    }

    /**
     * Getter returns the PowerUpHandler object stored in this world.
     * @return PowerUpHandler object for this world.
     */
    public PowerUpHandler getPowerUpHandler() {
        return this.powerUpGen;
    }

    /**
     * Getter returns the current level.
     * @return int current level for this world.
     */
    public int getLevelCount() {
        return levelCount;
    }

    /**
     * Returns the SoundClip object for the game music.
     * @return SoundClip object for the current game music.
     */
    public SoundClip getGameMusic() {
        return this.gameMusic;
    }

    /**
     * Getter returns the current volume.
     * @return double volume value.
     */
    public double getVolume() {
        return this.volume;
    }

    /**
     * Setter sets the level count based on int input
     * @param levelCount integer new level count.
     */
    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

    /**
     * Setter sets the volume based on a multiplier provided as a parameter.
     * @param vol double multiplier value for the volume.
     */
    public void setVolume(double vol) {
        this.volume = vol;
        this.gameMusic.setVolume(vol);
    }
}
