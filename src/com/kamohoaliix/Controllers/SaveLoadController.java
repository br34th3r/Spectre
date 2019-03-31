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

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       3.0.0
 */
public class SaveLoadController {
    // Implement necessary fields
    /**
     * The world this object affects
     */
    private CustomWorld world;
    /**
     * The player this object affects
     */
    private Player player;

    public SaveLoadController(CustomWorld world, Player player) {
        this.world = world;
        this.player = player;
    }

    /**
     * Saves a game state into a text file with the current date time format
     * @throws IOException
     */
    public void saveFile() throws IOException {
        // Format the current date time into a file name
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String fileName = formatter.format(date) + ".txt";

        // Create a space for the FileWriter to be created
        FileWriter writer = null;
        try {
            // Try to create the writer
            writer = new FileWriter(fileName);

            // Store the player regens, the score and the level count on separate lines in the file
            writer.write(player.getRegens() + "\n");
            writer.write(player.getScore() + "\n");
            writer.write(Integer.toString(world.getLevelCount()));
        } catch (IOException e) {
            // If there is an error, display it
            e.printStackTrace();
        } finally {
            // Then if writer isn't null, close it
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Loads a txt file with the specified fileName
     * @param fileName the path to the file the user wants to load
     */
    public void loadFile(String fileName) {
        try {
            // Try to create a new FileReader and BufferedReader space
            FileReader fr = null;
            BufferedReader reader = null;
            try {
                // Set the line count to 0
                int count = 0;

                // Create new FileReader and BufferedReader objects in their specified spaces
                fr = new FileReader(fileName);
                reader = new BufferedReader(fr);

                // Read the first line
                String line = reader.readLine();

                // While there is a value for the line, check if the count is 0, 1 or 2
                // and update the regens, score and level count appropriately
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

                    // Increment the count and read the next line
                    count++;
                    line = reader.readLine();
                }
            } finally {
                // Then if the BufferedReader and FileReader aren't null, close them
                if (reader != null) {
                    reader.close();
                }
                if (fr != null) {
                    fr.close();
                }
            }
        } catch (IOException e) {
            // If there is an error, display it
            e.printStackTrace();
        }
    }
}
