package com.kamohoaliix.Objects;

public class Player {
    private int score;
    private int level;

    public Player(int score) {
        this.score = score;
    }

    public void addScore(int value) {
        this.score = this.score + value;
    }

    public void removeScore(int value) {
        this.score = this.score - value;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isDead() {
        if(this.score < 0) {
            return true;
        } else {
            return false;
        }
    }
}
