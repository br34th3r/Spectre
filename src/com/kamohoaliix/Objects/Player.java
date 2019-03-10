package com.kamohoaliix.Objects;

public class Player {
    private int score;

    public Player() {
        this.score = 500;
    }

    public void changeScore(int increment) {
        this.score += increment;
    }

    public int getScore() {
        return this.score;
    }
}
