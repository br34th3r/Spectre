package com.kamohoaliix.Objects;


public class Player {
    private int score;
    private int regens;

    public Player() {
        this.score = 500;
    }

    public void changeScore(int increment) {
        this.score += increment;
    }

    public int getScore() {
        return this.score;
    }

    public void addRegen() {
        this.regens = this.regens + 1;
    }

    public int getRegens() {
        return this.regens;
    }

    public void resetRegens() {
        this.regens = 0;
    }

    public void resetScore() {
        this.score = 500;
    }

    public boolean isDead() {
        if(this.getScore() <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
