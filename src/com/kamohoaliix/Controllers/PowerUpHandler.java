package com.kamohoaliix.Controllers;

import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Node;
import com.kamohoaliix.Objects.Player;
import com.kamohoaliix.Objects.PowerUp;
import com.kamohoaliix.Objects.PowerUps.ClearRegens;
import com.kamohoaliix.Objects.PowerUps.ExtraPoints;

import java.util.Random;

/**
 * Handles the generation and storage of PowerUp objects of all types.
 */
public class PowerUpHandler implements PositionalGenerator {
    private World world;
    private UserView view;
    private NodeHandler nodeGen;
    private Player player;
    private PowerUp currentNewPowerup;
    private int powerUpCount = 3;
    private PowerUp[] powerUpArray;
    private Random rand = new Random();

    /**
     * Requires and stores all necessary values for
     * PowerUp generation, also calculates array size
     * based on the number of PowerUps to be made.
     * @param world the world in which the PowerUps will be generated and displayed.
     * @param view the view containing the world in which PowerUps will be displayed.
     * @param nodeGen the NodeHandler object as to check through the nodes to make sure there are no intersections.
     * @param player the current player.
     */
    public PowerUpHandler(World world, UserView view, NodeHandler nodeGen, Player player) {
        this.world = world;
        this.view = view;
        this.nodeGen = nodeGen;
        this.player = player;
        this.powerUpArray = new PowerUp[this.powerUpCount];
    }

    /**
     * Overrides the method in the PositionalGenerator
     * interface to generate a single co-ordinate based
     * on the Java Math library.
     * @return float value for a single x or y value.
     */
    @Override
    public float newPosition() {
        return this.rand.nextFloat() * 10 - 5;
    }

    /**
     * Creates a new basic PowerUp object that
     * does not have a type.
     * @return PowerUp object bland.
     */
    @Override
    public PowerUp newObject() {
        return new PowerUp(this.world, this.view, this.newPosition(), this.newPosition(), 0.5f, this.player, null);
    }

    /**
     * Creates a new ExtraPoints object that generates on
     * screen based on the newPosition() function.
     * @return an ExtraPoints object in a random position.
     */
    public ExtraPoints newExtraPoints() {
        return new ExtraPoints(this.world, this.view, this.newPosition(), this.newPosition(), 0.5f, this.player);
    }

    /**
     * Creates a new ClearRegens object that generates on
     * screen based on the newPosition() function.
     * @return a ClearRegens object in a random position.
     */
    public ClearRegens newClearRegens() {
        return new ClearRegens(this.world, this.view, this.newPosition(), this.newPosition(), 0.5f, this.player);
    }

    /**
     * Recursive algorithm that randomly generates either an
     * ExtraPoints object or a clear regens object
     * and then iterates over all nodes and powerups to make
     * sure that there are no obvious intersections
     * between them before outputting the final object.
     * @return PowerUp object that has been positionally checked for intersections.
     */
    @Override
    public PowerUp generateNewObject() {
        if(this.rand.nextBoolean()) {
            this.currentNewPowerup = this.newExtraPoints();
        } else {
            this.currentNewPowerup = this.newClearRegens();
        }
        for(PowerUp checkPowerup : this.powerUpArray) {
            if(checkPowerup != null && checkPowerup.intersects(this.currentNewPowerup)) {
                this.currentNewPowerup.destroy();
                this.currentNewPowerup = this.generateNewObject();
            }
        }
        for(Node checkNode : this.nodeGen.getNodeArray()) {
            if(checkNode != null && checkNode.intersects(this.currentNewPowerup)) {
                this.currentNewPowerup.destroy();
                this.currentNewPowerup = this.generateNewObject();
            }
        }
        return this.currentNewPowerup;
    }

    /**
     * Creates a new generation of PowerUps based on the
     * number of powerUps required to be created and
     * the aforementioned generateNewObject() function.
     */
    @Override
    public void newGeneration() {
        for(int i = this.powerUpCount - 1; i >= 0; i--) {
            this.powerUpArray[i] = this.generateNewObject();
        }
    }

    /**
     * Sequentially destroys all the PowerUp objects inside the powerUpArray and resets the array to an empty array.
     */
    public void resetPowerUpArray() {
        for(PowerUp powerUp : this.powerUpArray) {
            if(powerUp != null) {
                powerUp.destroy();
            }
        }
        this.powerUpArray = new PowerUp[this.powerUpCount];
    }

    /**
     * Getter method to return the powerUpArray
     * @return a PowerUp array containing all generated PowerUp objects.
     */
    public PowerUp[] getPowerUpArray() {
        return this.powerUpArray;
    }
}
