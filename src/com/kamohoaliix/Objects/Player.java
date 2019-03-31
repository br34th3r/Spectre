package com.kamohoaliix.Objects;

/**
 * @author      Joshua, Boddy, joshua.boddy@city.ac.uk
 * @version     3.0.0
 * @since       2.0.0
 */
public class Player {
    // Define score and regens variables
    /**
     * The player's score
     */
    private int score;
    /**
     * The number of regens the player has left
     */
    private int regens;

    /**
     * Constructor to initialise the
     * player with a score of 500
     */
    public Player() {
        this.score = 500;
        this.regens = 3;
    }

    /**
     * Increments the score by the increment provided,
     * if negative score will decrease.
     * @param increment amount to increment or decrement the score by.
     */
    public void changeScore(int increment) {
        this.score += increment;
    }

    /**
     * Returns the current player score.
     * @return integer value of player score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Add a regen to the player
     */
    public void removeRegen() {
        this.regens = this.regens - 1;
    }

    /**
     * Get the number of regens the player has used.
     * @return integer value of the number of regens.
     */
    public int getRegens() {
        return this.regens;
    }

    /**
     * Reset the number of regens to 3.
     */
    public void resetRegens() {
        this.regens = 3;
    }

    /**
     * Reset the score to the initial 500.
     */
    public void resetScore() {
        this.score = 500;
    }

    /**
     * Set the score directly by providing a score integer.
     * @param score integer to be set as new score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set the number of regens directly by providing an int regens.
     * @param regens number of regens to be set as the player's current total.
     */
    public void setRegens(int regens) {
        this.regens = regens;
    }

    /**
     * Checks if the player's score is less than or equal to 0.
     * @return boolean true if player is dead and false if not.
     */
    public boolean isDead() {
        if(this.getScore() <= 0 || this.getRegens() <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
