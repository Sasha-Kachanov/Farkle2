package com.kachanov.farkle.Helpers;

import java.util.Random;

/**
 * Created by sasha.kachanov on 4/29/2016.
 */
public class Die {
    private int value;
    private boolean selected;
    private boolean used;

    public Die() {
        value = 0;
        selected = false;
        used = true;
    }

    public void roll() {
        Random rand = new Random();
        value = rand.nextInt(6) + 1;
    }

    //getters and setters
    public int getValue() {
        return value;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}