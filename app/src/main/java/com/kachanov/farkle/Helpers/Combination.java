package com.kachanov.farkle.Helpers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mac on 3/9/16.
 */
public class Combination {
    private String name;
    private int points;
    private Footprint footprintOfTheCombination;
    private Integer nullInteger = new Integer(-10);

    public Combination() {
        clearAllFields();
    }

    public Combination(String name, int points, Footprint footprintOfTheCombination) {
        this.name = name;
        this.points = points;
        this.footprintOfTheCombination = footprintOfTheCombination;
    }

    //i need this method to reinitialize the combs array that holds availble combinations
    private void clearAllFields() {

        this.footprintOfTheCombination.setFootprintOfTheCombination(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0)));
        this.name = "empty";
        this.points = -1;
    }

    public ArrayList<Integer> getFootprintOfTheCombination() {
        return footprintOfTheCombination.getFootprintArrayList();
    }

    @Override
    public String toString() {
        return name + " - " + points;
    }
}
