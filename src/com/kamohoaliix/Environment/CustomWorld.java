package com.kamohoaliix.Environment;

import city.cs.engine.World;
import com.kamohoaliix.Controllers.ConnectionHandler;
import com.kamohoaliix.Controllers.NodeHandler;
import com.kamohoaliix.Controllers.PowerUpHandler;
import com.kamohoaliix.Objects.Player;

public class CustomWorld extends World {
    private Player player;
    private NodeHandler nodeGen;
    private PowerUpHandler powerUpGen;
    private ConnectionHandler connGen;
    private CustomView view;

    public CustomWorld(int fps, Player player) {
        super(fps);
        this.player = player;
        this.createView();
        this.createNewHandlers();
        this.newGeneration();
    }

    public void createView() {
        this.view = new CustomView(this, 500, 500, this.player);
    }

    public void createNewHandlers() {
        this.nodeGen = new NodeHandler(this, this.view);
        this.powerUpGen = new PowerUpHandler(this, view, nodeGen, player);
        this.connGen = new ConnectionHandler(this, nodeGen, powerUpGen, player);
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

    public CustomView getView() {
        return this.view;
    }

    public NodeHandler getNodeHandler() {
        return this.nodeGen;
    }

    public ConnectionHandler getConnectionHandler() {
        return this.connGen;
    }
}
