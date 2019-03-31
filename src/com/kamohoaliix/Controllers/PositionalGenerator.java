package com.kamohoaliix.Controllers;

import city.cs.engine.StaticBody;

import java.util.Random;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public interface PositionalGenerator {
    // Define functions required for PositionalGenerator implementations
    Random rand = new Random();
    float newPosition();
    StaticBody newObject();
    StaticBody generateNewObject();
    void newGeneration();
}
