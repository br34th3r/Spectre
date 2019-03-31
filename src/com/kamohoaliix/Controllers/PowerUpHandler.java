package com.kamohoaliix.Controllers;

import city.cs.engine.UserView;
import city.cs.engine.World;
import com.kamohoaliix.Objects.Node;
import com.kamohoaliix.Objects.Player;
import com.kamohoaliix.Objects.PowerUp;
import com.kamohoaliix.Objects.PowerUps.ClearRegens;
import com.kamohoaliix.Objects.PowerUps.ExtraPoints;

import java.util.Random;

public class PowerUpHandler implements PositionalGenerator {
    private World world;
    private UserView view;
    private NodeHandler nodeGen;
    private Player player;
    private PowerUp currentNewPowerup;
    private int powerUpCount = 3;
    private PowerUp[] powerUpArray;
    private Random rand = new Random();

    public PowerUpHandler(World world, UserView view, NodeHandler nodeGen, Player player) {
        this.world = world;
        this.view = view;
        this.nodeGen = nodeGen;
        this.player = player;
        this.powerUpArray = new PowerUp[this.powerUpCount];
    }

    @Override
    public float newPosition() {
        return this.rand.nextFloat() * 10 - 5;
    }

    @Override
    public PowerUp newObject() {
        return new PowerUp(this.world, this.view, this.newPosition(), this.newPosition(), 0.5f, this.player, null);
    }

    public ExtraPoints newExtraPoints() {
        return new ExtraPoints(this.world, this.view, this.newPosition(), this.newPosition(), 0.5f, this.player);
    }

    public ClearRegens newClearRegens() {
        return new ClearRegens(this.world, this.view, this.newPosition(), this.newPosition(), 0.5f, this.player);
    }

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

    @Override
    public void newGeneration() {
        for(int i = this.powerUpCount - 1; i >= 0; i--) {
            this.powerUpArray[i] = this.generateNewObject();
        }
    }

    public void resetPowerUpArray() {
        for(PowerUp powerUp : this.powerUpArray) {
            if(powerUp != null) {
                powerUp.destroy();
            }
        }
        this.powerUpArray = new PowerUp[this.powerUpCount];
    }

    public PowerUp[] getPowerUpArray() {
        return this.powerUpArray;
    }
}
