package com.kamohoaliix.Controllers;

import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Connection;
import com.kamohoaliix.Objects.Node;
import com.kamohoaliix.Objects.Player;
import com.kamohoaliix.Objects.PowerUp;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveLoadController {
    private CustomWorld world;
    private Player player;

    public SaveLoadController(CustomWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    public void saveFile() throws IOException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String fileName = formatter.format(date) + ".txt";
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write(this.player.getScore());
            writer.write(this.player.getRegens());
            writer.write(this.world.getLevelCount());
            writer.write(this.world.getNodeHandler().getColorCount());
            writer.write(this.world.getNodeHandler().getNodesOfEachColor());
            for(Node node : this.world.getNodeHandler().getNodeArray()) {
                writer.write("-");
                writer.write("" + node.getX());
                writer.write("" + node.getY());
                writer.write("" + node.getRadius());
                writer.write(node.getColor().toString());
                writer.write("" + node.getConnections());
            }
            writer.write("-");
            for(Connection conn : this.world.getConnectionHandler().getConnections()) {
                writer.write("-");
                writer.write("" + conn.getWidth());
                writer.write("" + conn.getHeight());
                writer.write("" + conn.getCenterX());
                writer.write("" + conn.getCenterY());
                writer.write("" + conn.getAngle());
                writer.write(conn.getConnectedNodes()[0].getX() + "," + conn.getConnectedNodes()[0].getY());
                writer.write(conn.getConnectedNodes()[1].getX() + "," + conn.getConnectedNodes()[1].getY());
                writer.write(conn.getColor().toString());
            }
            writer.write("-");
            for(PowerUp powerUp : this.world.getPowerUpHandler().getPowerUpArray()) {
                writer.write("-");
                writer.write("" + powerUp.getX());
                writer.write("" + powerUp.getY());
                writer.write("" + powerUp.getRadius());
                writer.write("" + powerUp.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
