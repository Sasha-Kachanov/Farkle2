package com.kachanov.farkle.Helpers;

import android.widget.Button;

/**
 * Created by sasha.kachanov on 4/30/2016.
 */
public class CombinationButton {
    private Button button;

    //i am not really using this combination anywhere but i should
    private Combination combination;

    public CombinationButton(Button button) {
        this.button = button;
        selected = false;
    }

    private boolean selected;

    //getters and setters
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Combination getCombination() {
        return combination;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }

}
