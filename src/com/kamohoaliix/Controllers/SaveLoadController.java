package com.kamohoaliix.Controllers;

import com.kamohoaliix.Environment.CustomWorld;
import com.kamohoaliix.Objects.Player;

import java.io.BufferedReader;
import java.io.FileReader;
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
            writer.write(player.getRegens() + "\n");
            writer.write(player.getScore() + "\n");
            writer.write(Integer.toString(world.getLevelCount()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public void loadFile(String fileName) {
        try {
            FileReader fr = null;
            BufferedReader reader = null;
            try {
                int count = 0;
                fr = new FileReader(fileName);
                reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    switch (count) {
                        case 0:
                            this.player.setRegens(Integer.parseInt(line));
                            break;
                        case 1:
                            this.player.setScore(Integer.parseInt(line));
                            break;
                        case 2:
                            this.world.setLevelCount(Integer.parseInt(line));
                            break;
                        default:
                            System.out.println("There's been a horrible misunderstanding");
                    }
                    count++;
                    line = reader.readLine();
                }
            } finally {
                if (reader != null) {
                    reader.close();
                }
                if (fr != null) {
                    fr.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
