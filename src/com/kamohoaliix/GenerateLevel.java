package com.kamohoaliix;

import city.cs.engine.World;
import com.kamohoaliix.Objects.Vertex;

import java.awt.*;
import java.util.Random;

public class GenerateLevel {
    private Vertex[][] vertices;
    private World world;
    private Random rand = new Random();
    private int arraySize1;
    private int arraySize2;

    public GenerateLevel(World world, int size1, int size2) {
        this.vertices = new Vertex[size1][size2];
        this.arraySize1 = size1;
        this.arraySize2 = size2;
        this.world = world;
        this.addVertices();
    }

    private void addVertices() {
        for(int i = 0; i < arraySize1; i++) {
            Color currentColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            for(int j = 0; j < arraySize2; j++) {
                this.vertices[i][j] = new Vertex(this.world, rand.nextFloat() * 10, rand.nextFloat() * 10, currentColor);
            }
        }
    }

    public void addMoreColors() {
        this.arraySize1 = this.arraySize1 + 1;
    }

    public void addMoreDots() {
        this.arraySize2 = this.arraySize2 + 1;
    }
}
