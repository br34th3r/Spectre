package com.kamohoaliix.Controllers;

import city.cs.engine.StaticBody;

import java.util.Random;

public interface PositionalGenerator {
    Random rand = new Random();

    float newPosition();
    StaticBody newObject();
    StaticBody generateNewObject();
    void newGeneration();
}
